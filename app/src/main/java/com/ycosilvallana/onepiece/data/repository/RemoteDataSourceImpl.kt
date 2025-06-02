package com.ycosilvallana.onepiece.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ycosilvallana.onepiece.data.local.OnePieceDatabase
import com.ycosilvallana.onepiece.data.paging_source.CharacterRemoteMediator
import com.ycosilvallana.onepiece.data.remote.OnePieceApi
import com.ycosilvallana.onepiece.domain.model.CharacterEntity
import com.ycosilvallana.onepiece.domain.repository.RemoteDataSource
import com.ycosilvallana.onepiece.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val onePieceApi: OnePieceApi,
    private val onePieceDatabase: OnePieceDatabase
): RemoteDataSource {

    private val characterDao = onePieceDatabase.characterDao()

    override fun getAllHeroes(): Flow<PagingData<CharacterEntity>> {
        val pagingSourceFactory = { characterDao.getAllCharacters() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = CharacterRemoteMediator(
                onePieceApi = onePieceApi,
                onePieceDatabase = onePieceDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(): Flow<PagingData<CharacterEntity>> {
        TODO("Not yet implemented")
    }
}