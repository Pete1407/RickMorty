package com.example.rickmorty.app.di

import com.example.rickmorty.app.domain.repository.CharacterRepository
import com.example.rickmorty.app.domain.usecase.GetAllCharacterUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideGetAllCharacterUseCase(repo : CharacterRepository):GetAllCharacterUsecase{
        return GetAllCharacterUsecase(repo)
    }
}