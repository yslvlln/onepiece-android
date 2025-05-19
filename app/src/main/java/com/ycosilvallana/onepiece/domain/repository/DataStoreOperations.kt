package com.ycosilvallana.onepiece.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnboardingStatus(completed: Boolean)
    fun readOnboardingStatus(): Flow<Boolean>
}