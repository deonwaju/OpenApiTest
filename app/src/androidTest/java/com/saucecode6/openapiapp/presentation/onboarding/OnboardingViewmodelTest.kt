package com.saucecode6.openapiapp.presentation.onboarding

import com.saucecode6.openapiapp.domain.usecases.AppEntryUsecases
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class OnboardingViewmodelTest {

    private lateinit var viewModel: OnboardingViewmodel
    private val appEntryUsecases = mockk<AppEntryUsecases>(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        viewModel = OnboardingViewmodel(appEntryUsecases)
    }

    @Test
    fun whenSaveAppEntryEventIsTriggered_saveAppEntryUsecaseIsCalled() = runTest(testDispatcher) {
        coEvery { appEntryUsecases.saveAppEntryUsecase() } returns Unit

        viewModel.onEvent(OnboardingEvent.SaveAppEntry)
        advanceUntilIdle()

        coVerify(exactly = 1) { appEntryUsecases.saveAppEntryUsecase() }
    }
}
