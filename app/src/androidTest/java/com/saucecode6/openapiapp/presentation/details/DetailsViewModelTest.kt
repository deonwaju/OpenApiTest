package com.saucecode6.openapiapp.presentation.details

import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import com.saucecode6.openapiapp.domain.usecases.NewsUsecases
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {

    private var newsUsecases: NewsUsecases = mockk()
    private val testDispatcher = StandardTestDispatcher()
    
    private val mockArticle = Article(
        author = "John Doe",
        content = "Sample content",
        description = "Sample description",
        publishedAt = "2024-10-01",
        source = Source(id = "123", name = "Sample Source"),
        title = "Sample Article",
        url = "https://example.com/sample-article",
        urlToImage = "https://example.com/sample-image.jpg"
    )

    private val viewModel by lazy {
        DetailsViewModel(newsUsecases)
    }

    @Before
    fun setup() {
        newsUsecases = mockk()
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun givenArticleDoesNotExist_whenUpsertDeleteArticleEventIsCalled_thenArticleIsSaved() = runTest {
        coEvery { newsUsecases.getLocalArticleByUrlUseCase(mockArticle.url) } returns null
        coEvery { newsUsecases.upsertArticleUseCase(mockArticle) } returns Unit

        viewModel.onEvent(DetailsEvent.UpsertDeleteArticle(mockArticle))
        advanceUntilIdle()

        assertEquals("Article Saved", viewModel.sideEffect)
    }

    @Test
    fun givenArticleExists_whenUpsertDeleteArticleEventIsCalled_thenArticleIsDeleted() = runTest {
        coEvery { newsUsecases.getLocalArticleByUrlUseCase(mockArticle.url) } returns mockArticle
        coEvery { newsUsecases.deleteArticleUseCase(mockArticle) } returns Unit

        viewModel.onEvent(DetailsEvent.UpsertDeleteArticle(mockArticle))
        advanceUntilIdle()

        assertEquals("Article Deleted", viewModel.sideEffect)
    }

    @Test
    fun whenRemoveSideEffectIsCalled_thenSideEffectIsCleared() = runTest {
        viewModel.onEvent(DetailsEvent.RemoveSideEffect)
        advanceUntilIdle()

        assertEquals(null, viewModel.sideEffect)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
