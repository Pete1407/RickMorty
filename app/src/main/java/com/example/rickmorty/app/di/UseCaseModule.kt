package com.example.rickmorty.app.di

import com.example.rickmorty.app.domain.repository.CharacterRepository
import com.example.rickmorty.app.domain.usecase.*
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

    @Provides
    fun getAlienSpeciesUseCase(repository: CharacterRepository):GetAlienSpeciesUsecase{
        return GetAlienSpeciesUsecase(repository)
    }

    @Provides
    fun getAnimalSpeciesUseCase(repository: CharacterRepository):GetAnimalSpeciesUsecase{
        return GetAnimalSpeciesUsecase(repository)
    }

    @Provides
    fun getUnknownSpeciesUseCase(repository: CharacterRepository):GetUnknownSpeciesUsecase{
        return GetUnknownSpeciesUsecase(repository)
    }
}