package com.example.rickmorty.app.di

import com.example.rickmorty.app.data.remote.ServiceAPI
import com.example.rickmorty.app.data.remote.datasource.CharacterRemoteDataSource
import com.example.rickmorty.app.data.remote.datasourceImpl.CharacterRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(serviceAPI: ServiceAPI):CharacterRemoteDataSource{
        return CharacterRemoteDataSourceImpl(serviceAPI)
    }
}