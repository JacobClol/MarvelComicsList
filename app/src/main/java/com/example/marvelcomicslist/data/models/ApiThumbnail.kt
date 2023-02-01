package com.example.marvelcomicslist.data.models

import com.google.gson.annotations.SerializedName

data class ApiThumbnail(
    @SerializedName("path")
    val urlImage: String,
    val extension: String
) {
    fun toImageComic(): String {
        return "$urlImage.$extension"
    }
}
