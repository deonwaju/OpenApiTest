package com.saucecode6.openapiapp.domain.di

import com.saucecode6.openapiapp.domain.repo.INewsRepository
import com.saucecode6.openapiapp.domain.usecases.DeleteArticleUseCase
import com.saucecode6.openapiapp.domain.usecases.GetLocalArticleByUrlUseCase
import com.saucecode6.openapiapp.domain.usecases.GetNewsUseCase
import com.saucecode6.openapiapp.domain.usecases.NewsUsecases
import com.saucecode6.openapiapp.domain.usecases.SearchNewsUseCase
import com.saucecode6.openapiapp.domain.usecases.UpsertArticleUseCase
import com.saucecode6.openapiapp.domain.usecases.AllLocalArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideNewsUseCases(iNewsRepository: INewsRepository): NewsUsecases = NewsUsecases(
        getNewsUseCase = GetNewsUseCase(iNewsRepository),
        searchNewsUseCase = SearchNewsUseCase(iNewsRepository),
        upsertArticleUseCase = UpsertArticleUseCase(iNewsRepository),
        deleteArticleUseCase = DeleteArticleUseCase(iNewsRepository),
        selectArticles = AllLocalArticlesUseCase(iNewsRepository),
        getLocalArticleByUrlUseCase = GetLocalArticleByUrlUseCase(iNewsRepository)
    )
}
