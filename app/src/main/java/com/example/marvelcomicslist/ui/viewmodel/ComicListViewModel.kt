package com.example.marvelcomicslist.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.marvelcomicslist.BuildConfig
import com.example.marvelcomicslist.core.base.AbstractViewModel
import com.example.marvelcomicslist.core.utils.CoroutinesContextProvider
import com.example.marvelcomicslist.domain.models.Comic
import com.example.marvelcomicslist.domain.models.ParamForGetComics
import com.example.marvelcomicslist.domain.usecases.GetComicsByHeroUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ComicListViewModel @Inject constructor(
    private val coroutinesContextProvider: CoroutinesContextProvider,
    private val getComicsByHeroUseCase: GetComicsByHeroUseCase,
) : AbstractViewModel<ViewModelScreenState>(ViewModelScreenState.Loading) {

    fun fetchComicByHero(name: String){
        viewModelScope.launch {
            mutableState.value = ViewModelScreenState.Loading
            try {
                val comics = withContext(coroutinesContextProvider.io){
                    getComicsByHeroUseCase(
                        ParamForGetComics(
                            ts = 1000,
                            apiKey = "ebabdc597eff5096f66bd9f42c282aee",
                            hash = "44625819ee7780a75f8dbf3e4355188b",
                            nameHero = name
                        )
                    )
                }
                handleGetComicsSuccess(comics)
            } catch (e: Exception){
                handleGetComicsError(e)
            }
        }
    }

    private fun handleGetComicsError(e: Exception) {
        val error = e.message ?: "No found comics for this hero"
        mutableState.value = ViewModelScreenState.Failure(error)
    }

    private fun handleGetComicsSuccess(comics: List<Comic>) {
        mutableState.value = ViewModelScreenState.Success(comics)
    }

    fun refresh(name: String){
        fetchComicByHero(name)
    }

}