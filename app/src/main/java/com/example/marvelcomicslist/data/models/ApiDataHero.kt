package com.example.marvelcomicslist.data.models

import com.example.marvelcomicslist.domain.models.Hero
import com.google.gson.annotations.SerializedName

data class ApiDataHero(
    @SerializedName("results")
    val listHeroData: List<ApiHero>
){
    fun toHeroData(): Hero = listHeroData.first().toHero()
}