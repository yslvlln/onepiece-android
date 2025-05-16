package com.ycosilvallana.onepiece.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ycosilvallana.onepiece.data.local.dao.CharacterDao
import com.ycosilvallana.onepiece.domain.model.Character

@Database(
    entities = [Character::class],
    version = 1
)
abstract class OnePieceDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}