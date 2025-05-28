package com.ycosilvallana.onepiece.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ycosilvallana.onepiece.util.Constants.CHARACTER_KEYS_DATABASE_TABLE

@Entity(tableName = CHARACTER_KEYS_DATABASE_TABLE)
data class CharacterRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val currentPage: Int,
    val lastPage: Int,
    val total: Int
)