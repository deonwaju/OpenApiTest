package com.saucecode6.openapiapp.data.usecases

import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import com.saucecode6.openapiapp.domain.repo.INewsRepository
import com.saucecode6.openapiapp.domain.usecases.AllLocalArticlesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AllLocalArticlesUseCaseTest {
    private val repository: INewsRepository = mockk()
    private lateinit var allLocalArticlesUseCase: AllLocalArticlesUseCase

    @Before
    fun setUp() {
        allLocalArticlesUseCase = AllLocalArticlesUseCase(repository)
    }

    @Test
    fun `invoke should call getArticles in repository`() = runTest {
        val articles = listOf(
            Article(
                author = "John Doe",
                content = "Sample content",
                description = "Sample description",
                publishedAt = "2024-10-01",
                source = Source(id = "123", name = "Sample Source"),
                title = "Sample Article",
                url = "https://example.com/sample-article",
                urlToImage = "https://example.com/sample-image.jpg"
            ),
            Article(
                author = "Miss John Doe",
                content = "Sample content2",
                description = "Sample description2",
                publishedAt = "2024-10-03",
                source = Source(id = "1233", name = "Sample Source2"),
                title = "Sample Article2",
                url = "https://example.com/sample-article2",
                urlToImage = "https://example.com/sample-image2.jpg"
            ),
        )
        val flow: Flow<List<Article>> = flowOf(articles)

        coEvery { repository.getArticles() } returns flow

        val result = allLocalArticlesUseCase().toList()
        verify(exactly = 1) { repository.getArticles() }
        assertEquals(articles, result.first())
    }
}
