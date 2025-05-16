package com.ycosilvallana.onepiece.di

import android.content.Context
import androidx.room.Room
import com.ycosilvallana.onepiece.data.local.OnePieceDatabase
import com.ycosilvallana.onepiece.util.Constants.ONE_PIECE_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        OnePieceDatabase::class.java,
        ONE_PIECE_DATABASE
    ).build()

}