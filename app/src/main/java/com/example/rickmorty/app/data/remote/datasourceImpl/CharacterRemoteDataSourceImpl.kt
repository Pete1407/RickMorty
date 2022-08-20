package com.example.rickmorty.app.data.remote.datasourceImpl

import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.remote.ServiceAPI
import com.example.rickmorty.app.data.remote.datasource.CharacterRemoteDataSource
import retrofit2.Response

class CharacterRemoteDataSourceImpl(
    private val serviceAPI: ServiceAPI
) : CharacterRemoteDataSource {

    override suspend fun getAllCharacters(): Response<Characters> {
        return serviceAPI.getAllCharacter()
    }

    override suspend fun getSingleCharacter(id: String): Response<Character> {
        return serviceAPI.getCharacter(id)
    }

    override suspend fun getHumanSpecies(specie: String): Response<Characters> {
        return serviceAPI.getCharacterBySpecie(specie)
    }

}