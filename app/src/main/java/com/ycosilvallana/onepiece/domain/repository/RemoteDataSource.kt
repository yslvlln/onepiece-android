package com.ycosilvallana.onepiece.domain.repository

import androidx.paging.PagingData
import com.ycosilvallana.onepiece.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Character>>
    fun searchHeroes(): Flow<PagingData<Character>>
}