package com.saucecode6.openapiapp.presentation.onboarding

sealed class OnboardingEvent {
    object SaveAppEntry: OnboardingEvent()
}