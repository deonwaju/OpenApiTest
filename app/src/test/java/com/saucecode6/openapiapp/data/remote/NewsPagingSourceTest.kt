package com.saucecode6.openapiapp.data.remote

import androidx.paging.PagingSource
import com.saucecode6.openapiapp.data.remote.api.NewsApi
import com.saucecode6.openapiapp.data.remote.dto.NewsResponse
import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NewsPagingSourceTest {

    private val mockApi: NewsApi = mockk()
    private val sources = "sample-source"

    @Test
    fun `load returns Page when successful`() = runTest {
        val mockArticles = listOf(
            Article(
                author = "Author1",
                content = "Content1",
                description = "Description1",
                publishedAt = "2024-10-05T12:00:00Z",
                source = Source(id = "source1", name = "Source 1"),
                title = "Title1",
                url = "http://example.com/article1",
                urlToImage = "http://example.com/image1"
            ),
            Article(
                author = "Author2",
                content = "Content2",
                description = "Description2",
                publishedAt = "2024-10-05T13:00:00Z",
                source = Source(id = "source2", name = "Source 2"),
                title = "Title2",
                url = "http://example.com/article2",
                urlToImage = "http://example.com/image2"
            ),
            Article(
                author = "Author3",
                content = "Content3",
                description = "Description3",
                publishedAt = "2024-10-05T13:00:00Z",
                source = Source(id = "source3", name = "Source 3"),
                title = "Title3",
                url = "http://example.com/article3",
                urlToImage = "http://example.com/image3"
            ),
        )
        val mockResponse = NewsResponse(
            totalResults = 3,
            articles = mockArticles,
            status = "ok"
        )

        coEvery { mockApi.getNews(1, sources) } returns mockResponse

        val pagingSource = NewsPagingSource(mockApi, sources)
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize =
                3,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Page)
        val pageResult = result as PagingSource.LoadResult.Page
        assertEquals(2, pageResult.data.size)
        assertEquals(2, pageResult.nextKey)
        assertNull(pageResult.prevKey)
    }
}
