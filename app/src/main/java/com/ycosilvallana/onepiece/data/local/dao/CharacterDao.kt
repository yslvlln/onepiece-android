package com.ycosilvallana.onepiece.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ycosilvallana.onepiece.domain.model.Character

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table ORDER BY id ASC")
    fun getAllCharacters(): PagingSource<Int, Character>

    @Query("SELECT * FROM character_table WHERE id=:characterId")
    fun getSelectedCharacter(characterId: Int): Character

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacters(characters: List<Character>)

    @Query("DELETE FROM character_table")
    suspend fun deleteAllCharacters()
}