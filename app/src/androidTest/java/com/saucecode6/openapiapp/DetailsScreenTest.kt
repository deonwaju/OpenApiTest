package com.saucecode6.openapiapp

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import com.saucecode6.openapiapp.presentation.details.DetailsEvent
import com.saucecode6.openapiapp.presentation.details.DetailsScreen
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockArticle = Article(
        author = "John Doe",
        title = "Test Article Title",
        description = "Test description",
        content = "Test content",
        publishedAt = "2023-06-16T22:24:33Z",
        source = Source(id = "bbc", name = "BBC"),
        url = "https://testarticle.com",
        urlToImage = "https://testimage.com/image.jpg"
    )

    @Test
    fun detailsScreen_displaysArticleDetails() {
        composeTestRule.setContent {
            DetailsScreen(article = mockArticle, event = {}, navigateUp = {})
        }

        // Verify that the title, content, and image are displayed
        composeTestRule.onNodeWithText("Test Article Title").assertExists()
        composeTestRule.onNodeWithText("Test content").assertExists()
    }

    @Test
    fun detailsScreen_clickOnBack_callsNavigateUp() {
        var navigateUpCalled = false

        composeTestRule.setContent {
            DetailsScreen(
                article = mockArticle,
                event = {},
                navigateUp = { navigateUpCalled = true }
            )
        }

        composeTestRule.onNodeWithContentDescription("Back").performClick()

        assert(navigateUpCalled)
    }

    @Test
    fun detailsScreen_clickOnBookmark_callsUpsertDeleteEvent() {
        var eventTriggered: DetailsEvent? = null

        composeTestRule.setContent {
            DetailsScreen(
                article = mockArticle,
                event = { eventTriggered = it },
                navigateUp = {}
            )
        }

        composeTestRule.onNodeWithContentDescription("Bookmark").performClick()

        assert(eventTriggered is DetailsEvent.UpsertDeleteArticle)
        assert((eventTriggered as DetailsEvent.UpsertDeleteArticle).article == mockArticle)
    }

    @Test
    fun detailsScreen_clickOnShare_startsShareIntent() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()

        composeTestRule.setContent {
            DetailsScreen(article = mockArticle, event = {}, navigateUp = {})
        }

        composeTestRule.onNodeWithContentDescription("Share").performClick()

        val startedIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_TEXT, mockArticle.url)
            type = "text/plain"
        }
        assert(startedIntent.resolveActivity(context.packageManager) != null)
    }

    @Test
    fun detailsScreen_clickOnBrowser_startsBrowserIntent() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()

        composeTestRule.setContent {
            DetailsScreen(article = mockArticle, event = {}, navigateUp = {})
        }

        composeTestRule.onNodeWithContentDescription("Browser").performClick()

        val browserIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(mockArticle.url)
        }
        assert(browserIntent.resolveActivity(context.packageManager) != null)
    }
}