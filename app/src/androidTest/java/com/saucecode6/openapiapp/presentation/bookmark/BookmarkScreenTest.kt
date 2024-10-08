package com.saucecode6.openapiapp.presentation.bookmark

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import org.junit.Rule
import org.junit.Test

class BookmarkScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bookmarkScreen_displaysBookmarkTitle() {
        val state = BookmarkState(articles = listOf())

        composeTestRule.setContent {
            BookmarkScreen(
                state = state,
                navigateToDetails = {}
            )
        }

        composeTestRule.onNodeWithText("Bookmark").assertExists()
    }

    @Test
    fun bookmarkScreen_displaysArticlesList_whenArticlesAreNotEmpty() {
        val article =  Article(author = "Author 1", content = "Content 1", description = "Description 1",
            publishedAt = "2024-01-01", source = Source("1", "Source 1"),
            title = "Title 1", url = "url1", urlToImage = "imageUrl1")
        val state = BookmarkState(articles = listOf(article))

        composeTestRule.setContent {
            BookmarkScreen(
                state = state,
                navigateToDetails = {}
            )
        }

        composeTestRule.onNodeWithText("Title 1").assertExists()
        composeTestRule.onNodeWithText("Source 1").assertExists()
        composeTestRule.onNodeWithText("2024-01-01").assertExists()
        composeTestRule.onNodeWithContentDescription("Thumbnail Icon").assertExists()
    }

    @Test
    fun bookmarkScreen_displaysEmptyScreen_whenArticlesAreEmpty() {
        val state = BookmarkState(articles = listOf())

        composeTestRule.setContent {
            BookmarkScreen(
                state = state,
                navigateToDetails = {}
            )
        }

        composeTestRule.onNodeWithText("You have not saved news so far !").assertExists()
        composeTestRule.onNodeWithContentDescription("Empty Screen Icon").assertExists()
    }

    @Test
    fun bookmarkScreen_navigatesToDetails_whenArticleClicked() {
        val article =  Article(author = "Author 1", content = "Content 1", description = "Description 1",
            publishedAt = "2024-01-01", source = Source("1", "Source 1"),
            title = "Title 1", url = "url1", urlToImage = "imageUrl1")
        val state = BookmarkState(articles = listOf(article))
        var articleClicked: Article? = null

        composeTestRule.setContent {
            BookmarkScreen(
                state = state,
                navigateToDetails = { articleClicked = it }
            )
        }

        composeTestRule.onNodeWithTag("ArticleCard").performClick()

        assert(articleClicked == article)
    }
}
