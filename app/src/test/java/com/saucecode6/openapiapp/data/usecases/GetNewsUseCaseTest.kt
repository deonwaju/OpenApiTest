package com.saucecode6.openapiapp.data.usecases

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import com.saucecode6.openapiapp.domain.repo.INewsRepository
import com.saucecode6.openapiapp.domain.usecases.GetNewsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetNewsUseCaseTest {

    private val iNewsRepository: INewsRepository = mockk()
    private val getNewsUseCase = GetNewsUseCase(iNewsRepository)

    @Test
    fun `invoke should call getNews on iNewsRepository with correct sources`() = runTest {
        val sources = listOf("source1", "source2")
        val expectedPagingData = flowOf(PagingData.empty<Article>())

        coEvery { iNewsRepository.getNews(sources) } returns expectedPagingData

        getNewsUseCase(sources)

        coVerify(exactly = 1) { iNewsRepository.getNews(sources) }
    }
}
