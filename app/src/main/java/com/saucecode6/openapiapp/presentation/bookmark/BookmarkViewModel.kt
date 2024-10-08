package com.saucecode6.openapiapp.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saucecode6.openapiapp.domain.usecases.NewsUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val newsUsecases: NewsUsecases
): ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    init { getArticles() }

    private fun getArticles() {
        newsUsecases.selectArticles()
            .onEach {
            _state.value = _state.value.copy(articles = it.reversed())
            }
            .catch { exception ->
                _state.value = _state.value.copy(
                    errorMessage = exception.message ?: "An error occurred while fetching articles"
                )
            }
            .launchIn(viewModelScope)
    }
}
