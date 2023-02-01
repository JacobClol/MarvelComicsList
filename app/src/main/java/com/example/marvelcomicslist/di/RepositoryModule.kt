package com.example.marvelcomicslist.di

import com.example.marvelcomicslist.data.repositories.GetCharacterRepositoryImpl
import com.example.marvelcomicslist.data.repositories.GetComicsRepositoryImpl
import com.example.marvelcomicslist.domain.repositories.CharacterRepository
import com.example.marvelcomicslist.domain.repositories.GetComicsRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Reusable
    abstract fun bindComicRepository(
        getComicsByHeroImpl: GetComicsRepositoryImpl
    ): GetComicsRepository

    @Binds
    @Reusable
    abstract fun binCharacterRepository(
        getCharacterImpl: GetCharacterRepositoryImpl
    ): CharacterRepository
}