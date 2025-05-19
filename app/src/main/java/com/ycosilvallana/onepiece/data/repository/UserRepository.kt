package com.ycosilvallana.onepiece.data.repository

import com.ycosilvallana.onepiece.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val dataStore: DataStoreOperations
) {

    suspend fun saveOnboardingStatus(completed: Boolean) {
        dataStore.saveOnboardingStatus(completed = completed)
    }

    fun readOnboardingStatus(): Flow<Boolean> {
        return dataStore.readOnboardingStatus()
    }
}