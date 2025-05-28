package com.ycosilvallana.onepiece.data.remote

import com.ycosilvallana.onepiece.domain.model.Character
import com.ycosilvallana.onepiece.domain.model.base.BasePageResponse
import com.ycosilvallana.onepiece.domain.model.base.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OnePieceApi {

    @GET("/onepiece/characters")
    suspend fun getAllCharacters(
        @Query("page") page: Int = 1
    ): BasePageResponse<List<Character>>

    @GET("/onepiece/characters/search")
    suspend fun searchCharacter(
        @Query("name") name: String
    ): BaseResponse<List<Character>>
}