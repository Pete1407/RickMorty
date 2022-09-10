package com.example.rickmorty.app.di

import com.example.rickmorty.app.data.remote.datasource.CharacterRemoteDataSource
import com.example.rickmorty.app.data.remote.datasource.LocationRemoteDataSource
import com.example.rickmorty.app.data.remote.repositoryImpl.CharacterRepositoryImpl
import com.example.rickmorty.app.data.remote.repositoryImpl.LocationRepositoryImpl
import com.example.rickmorty.app.domain.repository.CharacterRepository
import com.example.rickmorty.app.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(remoteDataSource: CharacterRemoteDataSource):CharacterRepository{
        return CharacterRepositoryImpl(remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideLocationRepository(remoteDataSource: LocationRemoteDataSource):LocationRepository{
        return LocationRepositoryImpl(remoteDataSource)
    }
}