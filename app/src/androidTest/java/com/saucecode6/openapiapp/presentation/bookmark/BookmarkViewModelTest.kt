package com.saucecode6.openapiapp.presentation.bookmark

import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import com.saucecode6.openapiapp.domain.usecases.NewsUsecases
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookmarkViewModelTest {

    private var newsUsecases: NewsUsecases = mockk()
    private val testDispatcher = StandardTestDispatcher()
    val mockArticles = listOf(
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

    private val viewModel by lazy {
        BookmarkViewModel(newsUsecases)
    }

    @Before
    fun setup() {
        newsUsecases = mockk()
        coEvery { newsUsecases.selectArticles() } returns flow {
            emit(mockArticles)
        }
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun givenSuccessfulArticleRetrieval_whenGetArticlesIsCalled_thenStateIsUpdatedWithReversedList() = runTest {
        coEvery { newsUsecases.selectArticles() } returns flowOf(mockArticles)

        viewModel
        advanceUntilIdle()

        assertEquals(mockArticles.reversed(), viewModel.state.value.articles)
    }

    @Test
    fun givenErrorInArticleRetrieval_whenGetArticlesFails_thenStateRemainsUnchanged() = runTest {
        val exception = Throwable("Error fetching articles")
        coEvery { newsUsecases.selectArticles() } returns flow {
            throw exception
        }

        viewModel
        advanceUntilIdle()

        val expectedState = BookmarkState(errorMessage = exception.message)
        assertEquals(expectedState.errorMessage, viewModel.state.value.errorMessage)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}