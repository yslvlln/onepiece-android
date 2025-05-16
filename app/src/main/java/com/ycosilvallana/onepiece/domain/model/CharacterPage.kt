package com.ycosilvallana.onepiece.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ycosilvallana.onepiece.util.Constants.CHARACTER_PAGE_DATABASE_TABLE

@Entity(tableName = CHARACTER_PAGE_DATABASE_TABLE)
data class CharacterPage(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val currentPage: Int,
    val lastPage: Int,
    val total: Int
)