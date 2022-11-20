package com.example.rickmorty.app.data.remote.datasourceImpl

import android.net.Uri
import android.util.Log
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.model.Info
import com.example.rickmorty.app.data.remote.ServiceAPI
import com.example.rickmorty.app.data.remote.datasource.CharacterRemoteDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class CharacterRemoteDataSourceImpl(
    private val serviceAPI: ServiceAPI
) : CharacterRemoteDataSource {

    override suspend fun getAllCharacters(next : String?): Response<Characters> {
        return serviceAPI.getAllCharacter(next)
    }

    override suspend fun getSingleCharacter(id: String): Response<Character> {
        return serviceAPI.getCharacter(id)
    }

    override suspend fun getHumanSpecies(specie: String): Response<Characters> {
        return serviceAPI.getCharacterBySpecie(specie)
    }

    override suspend fun getAlienSpecies(specie: String): Response<Characters> {
        return serviceAPI.getCharacterBySpecie(specie)
    }

    override suspend fun getAnimalSpecies(specie: String): Response<Characters> {
        return serviceAPI.getCharacterBySpecie(specie)
    }

    override suspend fun getUnknownSpecies(specie: String): Response<Characters> {
        return serviceAPI.getCharacterBySpecie(specie)
    }

    override suspend fun getCharacterBySearchingName(name: String): Response<Characters> {
        return serviceAPI.getCharacterBySeaching(name)
    }

}