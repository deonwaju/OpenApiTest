package com.saucecode6.openapiapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sauceCode6.openapiapp.R
import com.saucecode6.openapiapp.presentation.news_navigator.components.BottomNavigationItem
import com.saucecode6.openapiapp.presentation.news_navigator.components.NewsBottomNavigation
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsBottomNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testNewsBottomNavigationItemsDisplayedAndClickable() {
        val items =  listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home", "Home Button"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search", "Search Button"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "BookMark", "BookMark Button"),
        )

        composeTestRule.setContent {
            NewsBottomNavigation(
                items = items,
                selectedItem = 0,
                onItemClick = {}
            )
        }

        items.forEach { item ->
            composeTestRule.onNodeWithText(item.text).assertIsDisplayed()
            composeTestRule.onNodeWithContentDescription(item.description).assertIsDisplayed()
        }
    }

    @Test
    fun testHomeNewsBottomNavigationOnClick() {
        val items =  listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home", "Home Button"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search", "Search Button"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "BookMark", "BookMark Button"),
        )

        var clickedItemIndex = -1

        composeTestRule.setContent {
            NewsBottomNavigation(
                items = items,
                selectedItem = 0,
                onItemClick = { index -> clickedItemIndex = index }
            )
        }

        composeTestRule.onNodeWithContentDescription("Home Button").performClick()

        assert(clickedItemIndex == 0)
    }
 @Test
    fun testSearchNewsBottomNavigationOnClick() {
        val items =  listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home", "Home Button"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search", "Search Button"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "BookMark", "BookMark Button"),
        )

        var clickedItemIndex = -1

        composeTestRule.setContent {
            NewsBottomNavigation(
                items = items,
                selectedItem = 0,
                onItemClick = { index -> clickedItemIndex = index }
            )
        }

        composeTestRule.onNodeWithContentDescription("Search Button").performClick()

        assert(clickedItemIndex == 1)
    }
 @Test
    fun testBookMarkNewsBottomNavigationOnClick() {
        val items =  listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home", "Home Button"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search", "Search Button"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "BookMark", "BookMark Button"),
        )

        var clickedItemIndex = -1

        composeTestRule.setContent {
            NewsBottomNavigation(
                items = items,
                selectedItem = 0,
                onItemClick = { index -> clickedItemIndex = index }
            )
        }

        composeTestRule.onNodeWithContentDescription("BookMark Button").performClick()

        assert(clickedItemIndex == 2)
    }
}
