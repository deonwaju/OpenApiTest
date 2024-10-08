package com.saucecode6.openapiapp.domain.usecases

import com.saucecode6.openapiapp.domain.manager.ILocalUserManager

class SaveAppEntryUsecase(
    private val localUserManager: ILocalUserManager
) {
    suspend operator fun invoke() = localUserManager.saveAppEntity()
}
