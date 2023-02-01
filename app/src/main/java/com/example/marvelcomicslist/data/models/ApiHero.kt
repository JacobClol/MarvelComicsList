package com.example.marvelcomicslist.data.models

import com.example.marvelcomicslist.domain.models.Hero
import com.google.gson.annotations.SerializedName

data class ApiHero(
    val id: Int?,
    val name: String?,
    val description: String?,
    @SerializedName("comics")
    val dataComicHero: ApiComicResource,
    @SerializedName("thumbnail")
    val imageHero: ApiThumbnail
) {
    fun toHero() : Hero {
        return Hero(
            id = id,
            name = name,
            description = description,
            image = imageHero.toImageComic(),
            comicResource = dataComicHero.collectionURI
        )
    }
}
