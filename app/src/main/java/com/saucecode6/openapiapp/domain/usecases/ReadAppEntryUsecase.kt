package com.saucecode6.openapiapp.domain.usecases

import com.saucecode6.openapiapp.domain.manager.ILocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntryUsecase(
    private val localUserManager: ILocalUserManager
) {
    operator fun invoke(): Flow<Boolean> = localUserManager.readAppEntry()
}
