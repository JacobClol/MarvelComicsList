package com.example.marvelcomicslist.domain.usecases

import com.example.marvelcomicslist.domain.models.Comic
import com.example.marvelcomicslist.domain.models.ParamForGetComics
import com.example.marvelcomicslist.domain.repositories.GetComicsRepository
import javax.inject.Inject

class GetComicsByHeroUseCase @Inject constructor(
    private val getComicsRepository: GetComicsRepository
) {
    suspend operator fun invoke(params: ParamForGetComics): List<Comic> {
        return getComicsRepository.getComicsByHero(params)
    }
}