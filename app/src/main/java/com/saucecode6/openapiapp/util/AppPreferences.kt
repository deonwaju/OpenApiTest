package com.saucecode6.openapiapp.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

object AppPreferences {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)
    object PreferencesKeys{
        val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
    }
}