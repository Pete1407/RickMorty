package com.example.rickmorty.app.di

import com.example.rickmorty.app.domain.usecase.*
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
        getHumanSpeciesUsecase: GetHumanSpeciesUsecase,
        getAlienSpecieUsecase : GetAlienSpeciesUsecase,
        getAnimalSpeciesUsecase: GetAnimalSpeciesUsecase,
        getUnknownSpeciesUsecase: GetUnknownSpeciesUsecase,
        getAllCharacterUsecase: GetAllCharacterUsecase
    ):CharacterViewModelFactory{
        return CharacterViewModelFactory(
            getHumanSpeciesUsecase,
            getAlienSpecieUsecase,
            getAnimalSpeciesUsecase,
            getUnknownSpeciesUsecase,
            getAllCharacterUsecase
        )
    }
}