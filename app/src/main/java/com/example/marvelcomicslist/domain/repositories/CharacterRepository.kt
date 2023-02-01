package com.example.marvelcomicslist.domain.repositories

import com.example.marvelcomicslist.domain.models.Hero
import com.example.marvelcomicslist.domain.models.ParamForGetComics

interface CharacterRepository {
    suspend fun getCharacter(params: ParamForGetComics): Hero
}