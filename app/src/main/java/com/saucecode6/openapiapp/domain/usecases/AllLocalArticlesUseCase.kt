package com.saucecode6.openapiapp.domain.usecases

import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.repo.INewsRepository
import kotlinx.coroutines.flow.Flow

class AllLocalArticlesUseCase(
    private val repository: INewsRepository,
) {
    operator fun invoke(): Flow<List<Article>> = repository.getArticles()
}
