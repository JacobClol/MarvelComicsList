package com.example.marvelcomicslist.data.service

import com.example.marvelcomicslist.data.models.ApiCharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/v1/public/characters")
    suspend fun getDetailComic(
        @Query("name") name: String?,
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ) : ApiCharacterResponse
}