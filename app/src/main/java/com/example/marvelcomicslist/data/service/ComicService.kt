package com.example.marvelcomicslist.data.service

import com.example.marvelcomicslist.data.models.ApiComicsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicService {

    @GET("/v1/public/comics")
    suspend fun getComicsByHero(
        @Query("titleStartsWith") name: String,
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ) : ApiComicsResponse
}