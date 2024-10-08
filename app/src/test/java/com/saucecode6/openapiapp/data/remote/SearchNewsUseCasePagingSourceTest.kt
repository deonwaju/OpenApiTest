package com.saucecode6.openapiapp.data.remote

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.saucecode6.openapiapp.data.remote.api.NewsApi
import com.saucecode6.openapiapp.data.remote.dto.NewsResponse
import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchNewsUseCasePagingSourceTest {

    private val newsApi: NewsApi = mockk()
    private val searchQuery = "searchTerm"
    private val sources = "source1,source2"
    private val searchNewsPagingSource = SearchNewsPagingSource(newsApi, searchQuery, sources)

    @Test
    fun `load should return a page of articles on success`() = runBlocking {
        val articles = listOf(
            Article("Author1", "Content1", "Description1", "2024-01-01", Source("source1", "Source 1"), "Title 1", "url1", "imageUrl1"),
            Article("Author2", "Content2", "Description2", "2024-01-02", Source("source2", "Source 2"), "Title 2", "url2", "imageUrl2")
        )
        val newsResponse = NewsResponse(articles, "ok",totalResults = 2)
        
        coEvery { newsApi.searchNews(searchQuery = searchQuery, sources = sources, page = 1) } returns newsResponse

        val result = searchNewsPagingSource.load(PagingSource.LoadParams.Refresh(key = 1, loadSize = 10, placeholdersEnabled = false))
        assertTrue(result is PagingSource.LoadResult.Page)
        val page = result as PagingSource.LoadResult.Page
        assertEquals(articles, page.data)
        assertEquals(null, page.nextKey)
        assertEquals(null, page.prevKey)
    }

    @Test
    fun `load should return error when API call fails`() = runBlocking {
        val exception = Exception("Network Error")
        coEvery { newsApi.searchNews(searchQuery = searchQuery, sources = sources, page = 1) } throws exception

        val result = searchNewsPagingSource.load(PagingSource.LoadParams.Refresh(key = 1, loadSize = 10, placeholdersEnabled = false))

        assertTrue(result is PagingSource.LoadResult.Error)
        val errorResult = result as PagingSource.LoadResult.Error
        assertEquals(exception, errorResult.throwable)
    }
}