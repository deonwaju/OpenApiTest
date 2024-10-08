package com.saucecode6.openapiapp.presentation


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import com.saucecode6.openapiapp.domain.repo.INewsRepository
import com.saucecode6.openapiapp.domain.usecases.AllLocalArticlesUseCase
import com.saucecode6.openapiapp.domain.usecases.NewsUsecases
import com.saucecode6.openapiapp.presentation.bookmark.BookmarkState
import com.saucecode6.openapiapp.presentation.bookmark.BookmarkViewModel
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BookmarkViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository: INewsRepository = mockk()
    private val allLocalArticlesUseCase: AllLocalArticlesUseCase = AllLocalArticlesUseCase(repository)
    private val newsUsecases = NewsUsecases(
        getNewsUseCase = mockk(),
        searchNewsUseCase = mockk(),
        upsertArticleUseCase = mockk(),
        deleteArticleUseCase = mockk(),
        selectArticles = allLocalArticlesUseCase,
        getLocalArticleByUrlUseCase = mockk()
    )
    private lateinit var viewModel: BookmarkViewModel
    private val articles = listOf(
        Article(author = "Author 1", content = "Content 1", description = "Description 1",
            publishedAt = "2024-01-01", source = Source("1", "Source 1"),
            title = "Title 1", url = "url1", urlToImage = "imageUrl1"),
        Article(author = "Author 2", content = "Content 2", description = "Description 2",
            publishedAt = "2024-01-02", source = Source("2", "Source 2"),
            title = "Title 2", url = "url2", urlToImage = "imageUrl2")
    )

    @Before
    fun setUp() {
        // Initialize the ViewModel with mocked use cases
        viewModel = BookmarkViewModel(newsUsecases)
        coEvery { repository.getArticles() } returns flowOf(articles)

        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getArticles should update state with reversed articles`() = runTest {
        val articles = listOf(
            Article(author = "Author 1", content = "Content 1", description = "Description 1",
                publishedAt = "2024-01-01", source = Source("1", "Source 1"),
                title = "Title 1", url = "url1", urlToImage = "imageUrl1"),
            Article(author = "Author 2", content = "Content 2", description = "Description 2",
                publishedAt = "2024-01-02", source = Source("2", "Source 2"),
                title = "Title 2", url = "url2", urlToImage = "imageUrl2")
        )
        coEvery { repository.getArticles() } returns flowOf(articles)

        viewModel = BookmarkViewModel(newsUsecases)

        val expectedState = BookmarkState(articles = articles.reversed())
        assertEquals(expectedState, viewModel.state.value)
    }

    @Test
    fun `initial state should be empty articles`() {
        val expectedState = BookmarkState(articles = emptyList())

        assertEquals(expectedState, viewModel.state.value)
    }

    @Test
    fun `getArticles should update state with articles`() = runTest {
        val articles = listOf(
            Article(
                author = "Author 1",
                content = "Content 1",
                description = "Desc 1",
                publishedAt = "2024-01-01",
                source = Source("1", "Source 1"),
                title = "Title 1",
                url = "http://example.com/1",
                urlToImage = "http://example.com/image1"
            ),
            Article(
                author = "Author 2",
                content = "Content 2",
                description = "Desc 2",
                publishedAt = "2024-01-02",
                source = Source("2", "Source 2"),
                title = "Title 2",
                url = "http://example.com/2",
                urlToImage = "http://example.com/image2"
            ),
        )
        coEvery { newsUsecases.selectArticles() } returns flow { emit(articles) }
        coEvery { newsUsecases.selectArticles() } returns flow { emit(articles) }

        viewModel

        val expectedState = BookmarkState(articles = articles.reversed())
        assertEquals(expectedState, viewModel.state.value)
    }
}