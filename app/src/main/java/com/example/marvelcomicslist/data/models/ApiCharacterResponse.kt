package com.example.marvelcomicslist.data.models

import com.example.marvelcomicslist.domain.models.Hero
import com.google.gson.annotations.SerializedName

data class ApiCharacterResponse(
    @SerializedName("data")
    val dataHero: ApiDataHero
){
    fun toHeroResponse(): Hero = dataHero.toHeroData()
}
