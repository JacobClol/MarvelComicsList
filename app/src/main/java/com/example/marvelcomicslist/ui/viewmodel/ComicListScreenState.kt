package com.example.marvelcomicslist.ui.viewmodel

import com.example.marvelcomicslist.domain.models.Comic
import com.example.marvelcomicslist.domain.models.Hero

sealed class ComicListScreenState {
    object Loading : ComicListScreenState()

    class SuccessHero(val hero: Hero) : ComicListScreenState()

    class SuccessListComic(val comics: List<Comic>) : ComicListScreenState()

    class Failure(val error: String) : ComicListScreenState()
}
