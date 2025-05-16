package com.ycosilvallana.onepiece.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ycosilvallana.onepiece.data.local.dao.CharacterDao
import com.ycosilvallana.onepiece.data.local.dao.CharacterPageDao
import com.ycosilvallana.onepiece.domain.model.Character
import com.ycosilvallana.onepiece.domain.model.CharacterPage

@Database(
    entities = [Character::class, CharacterPage::class],
    version = 1
)
abstract class OnePieceDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun characterPageDao(): CharacterPageDao
}