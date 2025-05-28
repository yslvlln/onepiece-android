package com.ycosilvallana.onepiece.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ycosilvallana.onepiece.domain.model.CharacterRemoteKeys

@Dao
interface CharacterRemoteKeysDao {

    @Query("SELECT * FROM character_keys_table WHERE id = :characterId")
    suspend fun getRemoteKeys(characterId: Int): CharacterRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(characterRemoteKeys: List<CharacterRemoteKeys>)

    @Query("DELETE FROM character_keys_table")
    suspend fun deleteAllRemoteKeys()
}