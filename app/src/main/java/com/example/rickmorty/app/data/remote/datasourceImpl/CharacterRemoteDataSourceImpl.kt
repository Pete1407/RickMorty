package com.example.rickmorty.app.data.remote.datasourceImpl

import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.remote.ServiceAPI
import com.example.rickmorty.app.data.remote.datasource.CharacterRemoteDataSource
import retrofit2.Response

class CharacterRemoteDataSourceImpl(
    val serviceAPI: ServiceAPI
) : CharacterRemoteDataSource {

    override suspend fun getAllCharacters(): Response<Characters> {
        return serviceAPI.getAllCharacter()
    }

}