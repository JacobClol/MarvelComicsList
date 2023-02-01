package com.example.marvelcomicslist.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    val id: Int,
    val title: String,
    val description: String,
    val variantDescription: String,
    val image: String
) : Parcelable
