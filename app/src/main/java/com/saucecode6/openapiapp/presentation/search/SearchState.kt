package com.saucecode6.openapiapp.presentation.search

import androidx.paging.PagingData
import com.saucecode6.openapiapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val uiState: SearchUiState = SearchUiState.Default,
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null,
)

sealed class SearchUiState {
    data class Success(
        val articles: Flow<PagingData<Article>>? = null,
    ) : SearchUiState()

    object Loading : SearchUiState()
    object Default : SearchUiState()
}

enum class SearchUiStates {
    Default,
    Success,
    Loading,
}
