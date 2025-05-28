package com.ycosilvallana.onepiece.data.paging_source

import androidx.compose.runtime.key
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ycosilvallana.onepiece.data.local.OnePieceDatabase
import com.ycosilvallana.onepiece.data.remote.OnePieceApi
import com.ycosilvallana.onepiece.domain.model.Character
import com.ycosilvallana.onepiece.domain.model.CharacterRemoteKeys
import javax.inject.Inject

@ExperimentalPagingApi
class CharacterRemoteMediator @Inject constructor(
    private val onePieceApi: OnePieceApi,
    private val onePieceDatabase: OnePieceDatabase
) : RemoteMediator<Int, Character>() {

    private val characterDao = onePieceDatabase.characterDao()
    private val characterRemoteKeysDao = onePieceDatabase.characterKeysDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Character>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.currentPage ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.currentPage?.minus(1)
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.currentPage?.plus(1)
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }
            val response = onePieceApi.getAllCharacters(page = page)
            if (response.data.isNotEmpty()) {
                onePieceDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        characterDao.deleteAllCharacters()
                        characterRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val currentPage = response.meta.currentPage
                    val lastPage = response.meta.lastPage ?: 0
                    val totalPage = response.meta.total ?: 0
                    val keys = response.data.map { character ->
                        CharacterRemoteKeys(
                            id = character.id,
                            currentPage = currentPage,
                            lastPage = lastPage,
                            total = totalPage
                        )
                    }
                    characterRemoteKeysDao.addAllRemoteKeys(keys)
                    characterDao.addCharacters(characters = response.data)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.meta.currentPage == response.meta.total)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Character>
    ): CharacterRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { characterId ->
                characterRemoteKeysDao.getRemoteKeys(characterId = characterId)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Character>
    ): CharacterRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }
            ?.data
            ?.firstOrNull()
            ?.let { character ->
                characterRemoteKeysDao.getRemoteKeys(characterId = character.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Character>
    ): CharacterRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }
            ?.data
            ?.lastOrNull()
            ?.let { character ->
                characterRemoteKeysDao.getRemoteKeys(characterId = character.id)
            }
    }
}