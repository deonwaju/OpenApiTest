package com.saucecode6.openapiapp.data.usecases

import androidx.paging.PagingData
import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.repo.INewsRepository
import com.saucecode6.openapiapp.domain.usecases.SearchNewsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SearchNewsUseCaseTest {
    private val iNewsRepository: INewsRepository = mockk()
    private val searchNewsUseCase = SearchNewsUseCase(iNewsRepository)

    @Test
    fun `invoke should call searchNews in repository`() = runTest {
        val searchQuery = "Kotlin"
        val sources = listOf("TechCrunch", "BBC")
        val pagingData = PagingData.empty<Article>()
        coEvery { iNewsRepository.searchNews(searchQuery, sources) } returns flowOf(pagingData)

        val result: Flow<PagingData<Article>> = searchNewsUseCase(searchQuery, sources)

        verify(exactly = 1) { iNewsRepository.searchNews(searchQuery = searchQuery, sources = sources) }
        result.collect { pagingDataResult ->
            assert(pagingDataResult == pagingData)
        }
    }
}