package com.ycosilvallana.onepiece.data.repository

import androidx.paging.PagingData
import com.ycosilvallana.onepiece.domain.model.CharacterEntity
import com.ycosilvallana.onepiece.domain.repository.DataStoreOperations
import com.ycosilvallana.onepiece.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    fun getAllCharacters(): Flow<PagingData<CharacterEntity>> {
        return remote.getAllHeroes()
    }

    suspend fun saveOnboardingStatus(completed: Boolean) {
        dataStore.saveOnboardingStatus(completed = completed)
    }

    fun readOnboardingStatus(): Flow<Boolean> {
        return dataStore.readOnboardingStatus()
    }
}