package com.example.marvelcomicslist.domain.models

data class ParamForGetComics(
    val ts: Int,
    val apiKey: String,
    val hash: String,
    val nameHero: String? = null,
    val idHero: Int = 0
)
