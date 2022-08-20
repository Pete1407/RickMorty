package com.example.rickmorty.app.di

import com.example.rickmorty.app.domain.repository.CharacterRepository
import com.example.rickmorty.app.domain.usecase.GetAllCharacterUsecase
import com.example.rickmorty.app.domain.usecase.GetHumanSpeciesUsecase
import com.example.rickmorty.app.domain.usecase.GetSingleCharacterUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetCharacterUseCase(repository : CharacterRepository):GetSingleCharacterUsecase{
        return GetSingleCharacterUsecase(repository)
    }

    @Provides
    fun provideGetAllCharacterUseCase(repository : CharacterRepository):GetAllCharacterUsecase{
        return GetAllCharacterUsecase(repository)
    }

    @Provides
    fun getHumanSpeciesUseCase(repository: CharacterRepository):GetHumanSpeciesUsecase{
        return GetHumanSpeciesUsecase(repository)
    }
}