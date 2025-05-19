package com.ycosilvallana.onepiece.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.ycosilvallana.onepiece.domain.repository.DataStoreOperations
import com.ycosilvallana.onepiece.util.Constants.ONBOARDING_COMPLETED_KEY
import com.ycosilvallana.onepiece.util.Constants.ONE_PIECE_PREFERENCE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(ONE_PIECE_PREFERENCE)

class DataStoreOperationsImpl(context: Context) : DataStoreOperations {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = ONBOARDING_COMPLETED_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnboardingStatus(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnboardingStatus(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onboardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onboardingState
            }
    }
}