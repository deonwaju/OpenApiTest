package com.saucecode6.openapiapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saucecode6.openapiapp.domain.usecases.AppEntryUsecases
import com.saucecode6.openapiapp.presentation.onboarding.OnboardingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewmodel @Inject constructor(
    private val appEntryUsecases: AppEntryUsecases
): ViewModel() {

    fun onEvent(event: OnboardingEvent){
        when(event){
            is OnboardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUsecases.saveAppEntryUsecase()
        }
    }
}