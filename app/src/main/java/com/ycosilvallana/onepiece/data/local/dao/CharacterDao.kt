package com.ycosilvallana.onepiece.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ycosilvallana.onepiece.domain.model.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table ORDER BY id ASC")
    fun getAllCharacters(): PagingSource<Int, CharacterEntity>

    @Query("SELECT * FROM character_table WHERE id=:characterId")
    fun getSelectedCharacter(characterId: Int): CharacterEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<CharacterEntity>)

    @Query("DELETE FROM character_table")
    suspend fun deleteAllCharacters()
}