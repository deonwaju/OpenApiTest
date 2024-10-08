package com.saucecode6.openapiapp.data.usecases

import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import com.saucecode6.openapiapp.domain.repo.INewsRepository
import com.saucecode6.openapiapp.domain.usecases.UpsertArticleUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UpsertArticleUseCaseTest {

    private val repository: INewsRepository = mockk()
    private val upsertArticleUseCase = UpsertArticleUseCase(repository)

    @Test
    fun `invoke should call upsertArticle in repository`() = runTest {
        val article = Article(
            author = "John Doe",
            content = "Sample content",
            description = "Sample description",
            publishedAt = "2024-10-01",
            source = Source(id = "1", name = "TestSource"),
            title = "Sample Title",
            url = "http://sample.com",
            urlToImage = "http://sample.com/image.jpg"
        )
        coEvery { repository.upsertArticle(article) } returns Unit

        upsertArticleUseCase(article)

        coVerify { repository.upsertArticle(article) }
    }
}