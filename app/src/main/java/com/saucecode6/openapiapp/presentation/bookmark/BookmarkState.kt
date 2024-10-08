package com.saucecode6.openapiapp.presentation.bookmark

import com.saucecode6.openapiapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
