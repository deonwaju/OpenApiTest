package com.saucecode6.openapiapp.data.remote.dto

import com.saucecode6.openapiapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
