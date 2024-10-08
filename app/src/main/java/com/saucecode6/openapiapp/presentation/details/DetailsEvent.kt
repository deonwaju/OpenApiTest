package com.saucecode6.openapiapp.presentation.details

import com.saucecode6.openapiapp.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article): DetailsEvent()
    object RemoveSideEffect: DetailsEvent()
}
