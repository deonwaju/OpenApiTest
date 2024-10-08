package com.saucecode6.openapiapp.data.usecases

import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import com.saucecode6.openapiapp.domain.repo.INewsRepository
import com.saucecode6.openapiapp.domain.usecases.GetLocalArticleByUrlUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetLocalArticleByUrlUseCaseTest {

    private val repository: INewsRepository = mockk()
    private lateinit var getLocalArticleByUrlUseCase: GetLocalArticleByUrlUseCase

    @Before
    fun setUp() {
        getLocalArticleByUrlUseCase = GetLocalArticleByUrlUseCase(repository)
    }

    @Test
    fun `invoke should return article when article is found`() = runTest {
        val url = "https://example.com/sample-article"
        val expectedArticle = Article(
            author = "John Doe",
            content = "Sample content",
            description = "Sample description",
            publishedAt = "2024-10-01",
            source = Source(id = "123", name = "Sample Source"),
            title = "Sample Article",
            url = url,
            urlToImage = "https://example.com/sample-image.jpg"
        )

        coEvery { repository.getArticleByUrl(url) } returns expectedArticle

        val result = getLocalArticleByUrlUseCase(url)
        assertEquals(expectedArticle, result)
        coVerify(exactly = 1) { repository.getArticleByUrl(url) }
    }

    @Test
    fun `invoke should return null when no article is found`() = runTest {
        val url = "https://example.com/non-existent-article"

        coEvery { repository.getArticleByUrl(url) } returns null

        val result = getLocalArticleByUrlUseCase(url)
        assertEquals(null, result)
        coVerify(exactly = 1) { repository.getArticleByUrl(url) }
    }
}