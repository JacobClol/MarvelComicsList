package com.example.marvelcomicslist.data.models

import com.example.marvelcomicslist.domain.models.Comic
import com.google.gson.annotations.SerializedName

data class ApiComicsResponse(
    val code: Int,
    val status: String?,
    val copyright: String?,
    val attributionText: String?,
    @SerializedName("data")
    val comics: ApiDataComics
) {
    fun toListComicResponse(): List<Comic> = comics.toListComics()
}
