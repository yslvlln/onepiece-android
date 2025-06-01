package com.ycosilvallana.onepiece.di

import androidx.paging.ExperimentalPagingApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ycosilvallana.onepiece.data.local.OnePieceDatabase
import com.ycosilvallana.onepiece.data.remote.OnePieceApi
import com.ycosilvallana.onepiece.data.repository.RemoteDataSourceImpl
import com.ycosilvallana.onepiece.domain.repository.RemoteDataSource
import com.ycosilvallana.onepiece.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.MINUTES)
            .connectTimeout(15, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideOnePieceApi(retrofit: Retrofit): OnePieceApi {
        return retrofit.create(OnePieceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        onePieceApi: OnePieceApi,
        onePieceDatabase: OnePieceDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            onePieceApi = onePieceApi,
            onePieceDatabase = onePieceDatabase
        )
    }
}