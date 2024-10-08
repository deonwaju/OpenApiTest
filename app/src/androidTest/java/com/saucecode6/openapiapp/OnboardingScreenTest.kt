package com.saucecode6.openapiapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.saucecode6.openapiapp.presentation.onboarding.OnboardingEvent
import com.saucecode6.openapiapp.presentation.onboarding.OnboardingScreen
import org.junit.Rule
import org.junit.Test

class OnboardingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun onboardingScreen_displaysFirstPage_withNextButton() {
        composeTestRule.setContent {
            OnboardingScreen(event = {})
        }

        composeTestRule.onNodeWithText("Next").assertExists()
    }

    @Test
    fun onboardingScreen_navigatesToNextPage_whenNextButtonClicked() {
        composeTestRule.setContent {
            OnboardingScreen(event = {})
        }

        composeTestRule.onNodeWithText("Next").performClick()

        composeTestRule.onNodeWithText("Back").assertExists()
        composeTestRule.onNodeWithText("Next").assertExists()
    }

    @Test
    fun onboardingScreen_navigatesToLastPage_andDisplaysGetStarted() {
        composeTestRule.setContent {
            OnboardingScreen(event = {})
        }

        repeat(2) {
            composeTestRule.onNodeWithText("Next").performClick()
        }

        composeTestRule.onNodeWithText("GetStarted").assertExists()
    }

    @Test
    fun onboardingScreen_triggersEvent_whenGetStartedClicked() {
        var eventTriggered = false

        composeTestRule.setContent {
            OnboardingScreen(event = {
                if (it is OnboardingEvent.SaveAppEntry) {
                    eventTriggered = true
                }
            })
        }

        repeat(2) {
            composeTestRule.onNodeWithText("Next").performClick()
        }

        composeTestRule.onNodeWithText("Get Started").performClick()

        assert(eventTriggered)
    }
}
