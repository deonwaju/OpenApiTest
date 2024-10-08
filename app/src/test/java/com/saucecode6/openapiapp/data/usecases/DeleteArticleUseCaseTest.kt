package com.saucecode6.openapiapp.data.usecases

import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import com.saucecode6.openapiapp.domain.repo.INewsRepository
import com.saucecode6.openapiapp.domain.usecases.DeleteArticleUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteArticleUseCaseTest {

    private val repository: INewsRepository = mockk()
    private lateinit var deleteArticleUseCase: DeleteArticleUseCase

    @Before
    fun setUp() {
        deleteArticleUseCase = DeleteArticleUseCase(repository)
    }

    @Test
    fun `invoke should call deleteArticle in repository`() = runTest {
        val article = Article(
            author = "John Doe",
            content = "Sample content",
            description = "Sample description",
            publishedAt = "2024-10-01",
            source = Source(id = "123", name = "Sample Source"),
            title = "Sample Article",
            url = "https://example.com/sample-article",
            urlToImage = "https://example.com/sample-image.jpg"
        )
        coEvery { repository.deleteArticle(article) } returns Unit

        deleteArticleUseCase(article)

        coVerify { repository.deleteArticle(article) }
    }

    @Test(expected = Exception::class)
    fun `invoke should throw exception when repository fails to delete article`() = runTest {
        val article = Article(
            author = "John Doe",
            content = "Sample content",
            description = "Sample description",
            publishedAt = "2024-10-01",
            source = Source(id = "123", name = "Sample Source"),
            title = "Sample Article",
            url = "https://example.com/sample-article",
            urlToImage = "https://example.com/sample-image.jpg"
        )

        coEvery { repository.deleteArticle(article) } throws Exception("Deletion failed")

        try {
            deleteArticleUseCase(article)
        } catch (e: Exception) {
            println("Caught expected exception: ${e.message}")
            throw e
        }
    }
}
