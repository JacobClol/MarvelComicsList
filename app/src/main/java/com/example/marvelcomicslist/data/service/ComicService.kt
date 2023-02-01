package com.example.marvelcomicslist.data.service

import com.example.marvelcomicslist.data.models.ApiComicsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicService {

    @GET("v1/public/characters/{idHero}/comics")
    suspend fun getComicsByHero(
        @Path("idHero") idHero: Int,
        @Query("ts") ts: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ) : ApiComicsResponse
}