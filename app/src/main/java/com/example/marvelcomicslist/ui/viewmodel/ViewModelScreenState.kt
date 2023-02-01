package com.example.marvelcomicslist.ui.viewmodel

import com.example.marvelcomicslist.domain.models.Comic

sealed class ViewModelScreenState {
    object Loading : ViewModelScreenState()

    class Success(val comics: List<Comic>) : ViewModelScreenState()

    class Failure(val error: String) : ViewModelScreenState()
}
