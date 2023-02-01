package com.example.marvelcomicslist.data.repositories

import com.example.marvelcomicslist.data.service.ComicService
import com.example.marvelcomicslist.domain.models.Comic
import com.example.marvelcomicslist.domain.models.ParamForGetComics
import com.example.marvelcomicslist.domain.repositories.GetComicsRepository
import javax.inject.Inject

class GetComicsRepositoryImpl @Inject constructor(
    private val comicsService: ComicService
) : GetComicsRepository {
    override suspend fun getComicsByHero(params: ParamForGetComics): List<Comic> {
        return comicsService.getComicsByHero(
            params.idHero,
            params.ts,
            params.apiKey,
            params.hash
        ).toListComicResponse()
    }
}