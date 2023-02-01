package com.example.marvelcomicslist.data.models

import com.example.marvelcomicslist.domain.models.Comic
import com.google.gson.annotations.SerializedName

data class ApiComics(
    val id: Int,
    val title: String?,
    val variantDescription: String?,
    val description: String?,
    @SerializedName("thumbnail")
    val imageComic: ApiThumbnail
) {
    fun toComic(): Comic {
        return Comic(
            id = id,
            title = title,
            variantDescription = variantDescription,
            description = description,
            image = imageComic.toImageComic()
        )
    }
}