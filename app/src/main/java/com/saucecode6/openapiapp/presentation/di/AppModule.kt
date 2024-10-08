package com.saucecode6.openapiapp.presentation.di

import android.app.Application
import com.saucecode6.openapiapp.data.manager.LocalUserManagerImpl
import com.saucecode6.openapiapp.domain.manager.ILocalUserManager
import com.saucecode6.openapiapp.domain.usecases.AppEntryUsecases
import com.saucecode6.openapiapp.domain.usecases.ReadAppEntryUsecase
import com.saucecode6.openapiapp.domain.usecases.SaveAppEntryUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): ILocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUsecases(iLocalUserManager: ILocalUserManager): AppEntryUsecases =
        AppEntryUsecases(
            readAppEntryUsecase = ReadAppEntryUsecase(iLocalUserManager),
            saveAppEntryUsecase = SaveAppEntryUsecase(iLocalUserManager)
        )
}
