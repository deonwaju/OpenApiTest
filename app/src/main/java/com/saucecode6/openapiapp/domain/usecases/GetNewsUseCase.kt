package com.saucecode6.openapiapp.domain.usecases

import androidx.paging.PagingData
import com.saucecode6.openapiapp.domain.model.Article
import com.saucecode6.openapiapp.domain.repo.INewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase(
    private val iNewsRepository: INewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>>{
        return iNewsRepository.getNews(sources = sources)
    }
}
