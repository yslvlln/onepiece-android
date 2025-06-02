package com.ycosilvallana.onepiece.domain.repository

import androidx.paging.PagingData
import com.ycosilvallana.onepiece.domain.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<CharacterEntity>>
    fun searchHeroes(): Flow<PagingData<CharacterEntity>>
}