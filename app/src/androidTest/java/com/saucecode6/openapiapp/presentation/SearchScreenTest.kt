package com.saucecode6.openapiapp.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.paging.PagingData
import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.model.Source
import com.saucecode6.openapiapp.presentation.search.SearchEvent
import com.saucecode6.openapiapp.presentation.search.SearchScreen
import com.saucecode6.openapiapp.presentation.search.SearchState
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSearchBarInput_andArticlesDisplay() {
        val testArticles = listOf(
            Article(
                author = "John Doe",
                title = "Test Article 1",
                description = "Test Description 1",
                content = "Test Content 1",
                publishedAt = "2024-10-08",
                source = Source(id = "1", name = "BBC News"),
                url = "https://example.com",
                urlToImage = "https://example.com/image.jpg"
            ),
            Article(
                author = "Jane Smith",
                title = "Breaking News",
                description = "Test Description 2",
                content = "Test Content 2",
                publishedAt = "2024-10-07",
                source = Source(id = "2", name = "CNN"),
                url = "https://example.com/2",
                urlToImage = "https://example.com/image2.jpg"
            )
        )

        val testState = SearchState(
            searchQuery = "",
            articles = flowOf(PagingData.from(testArticles))
        )
        val eventHandler = mutableStateOf<SearchEvent?>(null)
        composeTestRule.setContent {
            SearchScreen(
                state = testState,
                event = { eventHandler.value = it },
                navigateToDetails = {}
            )
        }

        composeTestRule.onNodeWithText("Search").assertIsDisplayed()
        composeTestRule.onNodeWithText("Search").performTextInput("Breaking News")

        assert(eventHandler.value == SearchEvent.UpdateSearchQuery("Breaking News"))
    }
}
