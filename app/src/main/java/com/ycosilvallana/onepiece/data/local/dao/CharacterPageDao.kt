package com.ycosilvallana.onepiece.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ycosilvallana.onepiece.domain.model.CharacterPage

@Dao
interface CharacterPageDao {

    @Query("SELECT * FROM character_page_table WHERE id = :id")
    suspend fun getPage(id: Int): CharacterPage?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllPages(characterPages: List<CharacterPage>)

    @Query("DELETE FROM character_page_table")
    suspend fun deleteAllPages()
}