package com.example.marvelcomicslist.data.models

import com.example.marvelcomicslist.domain.models.Comic
import com.google.gson.annotations.SerializedName

data class ApiDataComics(
    @SerializedName("results")
    val listComics: List<ApiComics>
) {
    fun toListComics(): List<Comic> =
        listComics.map {
            it.toComic()
        }
}