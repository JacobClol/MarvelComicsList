package com.example.marvelcomicslist.di

import com.example.marvelcomicslist.domain.repositories.GetComicsRepository
import com.example.marvelcomicslist.domain.repositories.CharacterRepository
import com.example.marvelcomicslist.domain.usecases.GetComicsByHeroUseCase
import com.example.marvelcomicslist.domain.usecases.GetCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Reusable
    fun provideGetComicsByHeroUsecase(
        getComicsRepository: GetComicsRepository
    ) = GetComicsByHeroUseCase(getComicsRepository)

    @Provides
    @Reusable
    fun provideGetDetailComicUseCse(
        getCharacterRepository: CharacterRepository
    ) = GetCharacterUseCase(getCharacterRepository)
}