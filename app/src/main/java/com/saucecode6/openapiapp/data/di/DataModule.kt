package com.saucecode6.openapiapp.data.di

import android.app.Application
import androidx.room.Room
import com.saucecode6.openapiapp.data.local.NewsDao
import com.saucecode6.openapiapp.data.local.NewsDatabase
import com.saucecode6.openapiapp.data.local.NewsTypeConverter
import com.saucecode6.openapiapp.data.remote.api.NewsApi
import com.saucecode6.openapiapp.data.repo.NewsRepositoryImpl
import com.saucecode6.openapiapp.domain.repo.INewsRepository
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
object DataModule {

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

    @Provides
    @Singleton
    fun provideINewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): INewsRepository = NewsRepositoryImpl(newsApi, newsDao)
}
