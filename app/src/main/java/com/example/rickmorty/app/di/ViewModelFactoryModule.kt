package com.example.rickmorty.app.di

import com.example.rickmorty.app.domain.usecase.GetAllCharacterUsecase
import com.example.rickmorty.app.domain.usecase.GetSingleCharacterUsecase
import com.example.rickmorty.feature.character.CharacterViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Singleton
    @Provides
    fun provideViewCharacterViewModelFactory(
        getAllCharacterUsecase: GetAllCharacterUsecase,
        getCharacterUsecase : GetSingleCharacterUsecase
    ):CharacterViewModelFactory{
        return CharacterViewModelFactory(getAllCharacterUsecase,getCharacterUsecase)
    }
}