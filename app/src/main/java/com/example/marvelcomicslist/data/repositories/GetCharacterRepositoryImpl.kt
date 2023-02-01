package com.example.marvelcomicslist.data.repositories

import com.example.marvelcomicslist.data.service.CharacterService
import com.example.marvelcomicslist.domain.models.Hero
import com.example.marvelcomicslist.domain.models.ParamForGetComics
import com.example.marvelcomicslist.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharacterRepositoryImpl @Inject constructor(
    private val charactersService: CharacterService
) : CharacterRepository {
    override suspend fun getCharacter(params: ParamForGetComics): Hero {
        return charactersService.getDetailComic(
            params.nameHero,
            params.ts,
            params.apiKey,
            params.hash
        ).toHeroResponse()
    }

}