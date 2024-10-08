package com.saucecode6.openapiapp.domain.usecases

import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.repo.INewsRepository

class GetLocalArticleByUrlUseCase(
    private val repository: INewsRepository
) {
    suspend operator fun invoke(url: String): Article? = repository.getArticleByUrl(url)
}
