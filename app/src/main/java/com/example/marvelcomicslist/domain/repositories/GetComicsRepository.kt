package com.example.marvelcomicslist.domain.repositories

import com.example.marvelcomicslist.domain.models.Comic
import com.example.marvelcomicslist.domain.models.ParamForGetComics

interface GetComicsRepository {
    suspend fun getComicsByHero(params: ParamForGetComics): List<Comic>
}