package com.example.marvelcomicslist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.marvelcomicslist.domain.models.Comic
import com.example.marvelcomicslist.domain.models.Hero
import com.example.marvelcomicslist.domain.models.ParamForGetComics
import com.example.marvelcomicslist.domain.usecases.GetCharacterUseCase
import com.example.marvelcomicslist.domain.usecases.GetComicsByHeroUseCase
import com.example.marvelcomicslist.ui.viewmodel.ComicListScreenState
import com.example.marvelcomicslist.ui.viewmodel.ComicListViewModel
import com.example.marvelcomicslist.utils.CoroutineTestRule
import com.example.marvelcomicslist.utils.relaxedMockk
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ComicListViewModelTest {

    @get:Rule
    val instanExecutorRule = InstantTaskExecutorRule()

    @get: Rule
    val coroutineRule = CoroutineTestRule()
    private val getComicsByHeroUseCase = relaxedMockk<GetComicsByHeroUseCase>()
    private val characterUseCase = relaxedMockk<GetCharacterUseCase>()
    private val observer = relaxedMockk<Observer<ComicListScreenState>>()
    private lateinit var viewModel: ComicListViewModel

    private val mockHero = Hero(
        id = 1011,
        name = "hulk",
        description = "Marvel hero super power",
        image = "https://i.annihil.us/u/prod/marvel/i/mg/5/a0/538615ca33ab0.jpg"
    )

    private val mockListComic = listOf<Comic>(
        Comic(
            id = 1,
            title = "Hulk comic 1",
            description = "Hulk description comic 1",
            variantDescription = null,
            image =  "https://i.annihil.us/u/prod/marvel/i/mg/5/a0/538615ca33ab0.jpg"
        ),
        Comic(
            id = 2,
            title = "Hulk comic two",
            description = "Hulk description comic two",
            variantDescription = "variant",
            image =  "https://i.annihil.us/u/prod/marvel/i/mg/5/a0/538615ca33ab0.jpg"
        )
    )

    private val mockParams = ParamForGetComics(
        ts = 1000,
        apiKey = "ebabdc597eff5096f66bd9f42c282aee",
        hash = "44625819ee7780a75f8dbf3e4355188b",
        nameHero = "hulk",
        idHero = 0
    )

    @Before
    fun setup(){
        viewModel = ComicListViewModel(
            coroutineRule.coroutineContextProvider,
            characterUseCase,
            getComicsByHeroUseCase
        )
    }

    @Test
    fun `when ComicListViewModel is created then shoul get comic by hero list and cath SuccessHero state`(){
        val slots = mutableListOf<ComicListScreenState>()
        coEvery {
            characterUseCase(mockParams)
        } answers {
            mockHero
        }
        coEvery {
            getComicsByHeroUseCase(mockParams)
        } answers {
            mockListComic
        }

        viewModel.fetchDataHero("hulk")
        viewModel.state.asLiveData().observeForever(observer)

        verify {
            observer.onChanged(capture(slots))
        }

        Assert.assertTrue(slots.last() is ComicListScreenState.SuccessHero)

        coVerify(exactly = 1) {
            characterUseCase.invoke(mockParams)
        }
    }
}