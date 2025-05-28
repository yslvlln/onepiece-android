package com.ycosilvallana.onepiece.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ycosilvallana.onepiece.data.local.dao.CharacterDao
import com.ycosilvallana.onepiece.data.local.dao.CharacterRemoteKeysDao
import com.ycosilvallana.onepiece.domain.model.Character
import com.ycosilvallana.onepiece.domain.model.CharacterRemoteKeys

@Database(
    entities = [Character::class, CharacterRemoteKeys::class],
    version = 1
)
@TypeConverters(DatabaseConverter::class)
abstract class OnePieceDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun characterKeysDao(): CharacterRemoteKeysDao
}