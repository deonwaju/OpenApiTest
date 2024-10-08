package com.saucecode6.openapiapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.ItemSnapshotList
import androidx.paging.compose.LazyPagingItems
import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import com.saucecode6.openapiapp.presentation.home.HomeScreen
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    val article = Article(
        author = "Author 1",
        content = "Content 1",
        description = "Description 1",
        publishedAt = "2024-01-01",
        source = Source("1", "Source 1"),
        title = "Title 1",
        url = "url1",
        urlToImage = "imageUrl1"
    )

    private val articles: LazyPagingItems<Article> = mockLazyPagingItems(listOf(article))

    @Test
    fun homeScreen_displaysSearchBar() {
        composeTestRule.setContent {
            HomeScreen(
                articles = articles,
                navigateToSearch = {},
                navigateToDetails = {}
            )
        }

        composeTestRule.onNodeWithText("").assertExists()  // SearchBar exists with empty text
    }

    @Test
    fun homeScreen_displaysMarqueeTitles_whenArticlesMoreThan10() {
        val articles = mockLazyPagingItems(
            List(11) { index ->
                Article(
                    author = "Author $index",
                    content = "Content $index",
                    description = "Description $index",
                    publishedAt = "2024-01-0$index",
                    source = Source(id = "$index", name = "Source $index"),
                    title = "Title $index",
                    url = "url$index",
                    urlToImage = "imageUrl$index"
                )
            }
        )

        composeTestRule.setContent {
            HomeScreen(
                articles = articles,
                navigateToSearch = {},
                navigateToDetails = {}
            )
        }

        val expectedMarqueeText = (0..9).joinToString(separator = " \uD83d\uDFE5") { "Title $it" }
        composeTestRule.onNodeWithText(expectedMarqueeText).assertExists()
    }

    @Test
    fun homeScreen_displaysArticlesList_whenArticlesAreNotEmpty() {
        val article = Article(
            author = "Author 1",
            content = "Content 1",
            description = "Description 1",
            publishedAt = "2024-01-01",
            source = Source("1", "Source 1"),
            title = "Title 1",
            url = "url1",
            urlToImage = "imageUrl1"
        )

        val articles = mockLazyPagingItems(listOf(article))

        composeTestRule.setContent {
            HomeScreen(
                articles = articles,
                navigateToSearch = {},
                navigateToDetails = {}
            )
        }

        composeTestRule.onNodeWithText("Title 1").assertExists()
        composeTestRule.onNodeWithText("Source 1").assertExists()
        composeTestRule.onNodeWithText("2024-01-01").assertExists()
    }

    @Test
    fun homeScreen_navigatesToSearch_whenSearchBarClicked() {
        val article = Article(
            author = "Author 1",
            content = "Content 1",
            description = "Description 1",
            publishedAt = "2024-01-01",
            source = Source("1", "Source 1"),
            title = "Title 1",
            url = "url1",
            urlToImage = "imageUrl1"
        )
        val articles = mockLazyPagingItems(listOf(article))
        var searchClicked = false

        composeTestRule.setContent {
            HomeScreen(
                articles = articles,
                navigateToSearch = { searchClicked = true },
                navigateToDetails = {}
            )
        }

        composeTestRule.onNodeWithTag("SearchBar Tag").performClick()
        assert(searchClicked)
    }

    @Test
    fun homeScreen_navigatesToDetails_whenArticleClicked() {
        val article = Article(
            author = "Author 1",
            content = "Content 1",
            description = "Description 1",
            publishedAt = "2024-01-01",
            source = Source("1", "Source 1"),
            title = "Title 1",
            url = "url1",
            urlToImage = "imageUrl1"
        )
        val articles = mockLazyPagingItems(listOf(article))
        var articleClicked: Article? = null

        composeTestRule.setContent {
            HomeScreen(
                articles = articles,
                navigateToSearch = {},
                navigateToDetails = { articleClicked = it }
            )
        }

        composeTestRule.onNodeWithTag("ArticleCard").performClick()

        assert(articleClicked == article)
    }

    private fun <T : Any> mockLazyPagingItems(items: List<T>): LazyPagingItems<T> {
        val pagingItems = mockk<LazyPagingItems<T>>(relaxed = true)

        every { pagingItems.itemCount } returns items.size
        every { pagingItems.itemSnapshotList } returns ItemSnapshotList( 0, items = items, placeholdersAfter = 0)
        items.forEachIndexed { index, item ->
            every { pagingItems[index] } returns item
        }

        return pagingItems
    }
}
