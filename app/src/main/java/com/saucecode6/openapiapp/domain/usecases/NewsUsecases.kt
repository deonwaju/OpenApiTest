package com.saucecode6.openapiapp.domain.usecases

data class NewsUsecases(
    val getNewsUseCase: GetNewsUseCase,
    val searchNewsUseCase: SearchNewsUseCase,
    val upsertArticleUseCase: UpsertArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val selectArticles: AllLocalArticlesUseCase,
    val getLocalArticleByUrlUseCase: GetLocalArticleByUrlUseCase,
)
