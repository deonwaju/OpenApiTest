package com.saucecode6.openapiapp.di

import android.app.Application
import androidx.room.Room
import com.saucecode6.openapiapp.data.local.NewsDao
import com.saucecode6.openapiapp.data.local.NewsDatabase
import com.saucecode6.openapiapp.data.local.NewsTypeConverter
import com.saucecode6.openapiapp.data.manager.LocalUserManagerImpl
import com.saucecode6.openapiapp.data.remote.api.NewsApi
import com.saucecode6.openapiapp.data.repo.NewsRepositoryImpl
import com.saucecode6.openapiapp.domain.manager.ILocalUserManager
import com.saucecode6.openapiapp.domain.repo.INewsRepository
import com.saucecode6.openapiapp.domain.usecases.AppEntryUsecases
import com.saucecode6.openapiapp.domain.usecases.DeleteArticleUseCase
import com.saucecode6.openapiapp.domain.usecases.GetNewsUseCase
import com.saucecode6.openapiapp.domain.usecases.NewsUsecases
import com.saucecode6.openapiapp.domain.usecases.ReadAppEntryUsecase
import com.saucecode6.openapiapp.domain.usecases.SaveAppEntryUsecase
import com.saucecode6.openapiapp.domain.usecases.SearchNewsUseCase
import com.saucecode6.openapiapp.domain.usecases.GetLocalArticleByUrlUseCase
import com.saucecode6.openapiapp.domain.usecases.AllLocalArticlesUseCase
import com.saucecode6.openapiapp.domain.usecases.UpsertArticleUseCase
import com.saucecode6.openapiapp.util.Constants.BASE_URL
import com.saucecode6.openapiapp.util.Constants.NEWS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): ILocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUsecases(
        iLocalUserManager: ILocalUserManager
    ) = AppEntryUsecases(
        readAppEntryUsecase = ReadAppEntryUsecase(iLocalUserManager),
        saveAppEntryUsecase = SaveAppEntryUsecase(iLocalUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideINewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): INewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        iNewsRepository: INewsRepository,
    ): NewsUsecases {
        return NewsUsecases(
            getNewsUseCase = GetNewsUseCase(iNewsRepository),
            searchNewsUseCase = SearchNewsUseCase(iNewsRepository),
            upsertArticleUseCase = UpsertArticleUseCase(iNewsRepository),
            deleteArticleUseCase = DeleteArticleUseCase(iNewsRepository),
            selectArticles = AllLocalArticlesUseCase(iNewsRepository),
            getLocalArticleByUrlUseCase = GetLocalArticleByUrlUseCase(iNewsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDataBase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}
