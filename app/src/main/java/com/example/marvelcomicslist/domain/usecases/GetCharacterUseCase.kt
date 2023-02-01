package com.example.marvelcomicslist.domain.usecases

import com.example.marvelcomicslist.domain.models.Hero
import com.example.marvelcomicslist.domain.models.ParamForGetComics
import com.example.marvelcomicslist.domain.repositories.CharacterRepository
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val getCharacterRepository: CharacterRepository
) {
    suspend operator fun invoke(params: ParamForGetComics): Hero {
        return getCharacterRepository.getCharacter(params)
    }
}