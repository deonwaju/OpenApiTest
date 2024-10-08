package com.saucecode6.openapiapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface ILocalUserManager {
    suspend fun saveAppEntity()

    fun readAppEntry(): Flow<Boolean>
}
