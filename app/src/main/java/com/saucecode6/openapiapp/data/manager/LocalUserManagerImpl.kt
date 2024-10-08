package com.saucecode6.openapiapp.data.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.saucecode6.openapiapp.domain.manager.ILocalUserManager
import com.saucecode6.openapiapp.util.AppPreferences
import com.saucecode6.openapiapp.util.AppPreferences.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(
    private val context: Context
): ILocalUserManager {
    override suspend fun saveAppEntity() {
        context.dataStore.edit { settings ->
            settings[AppPreferences.PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[AppPreferences.PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}
