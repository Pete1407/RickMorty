package com.example.rickmorty.app.data.remote.datasource

import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.model.Info
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CharacterRemoteDataSource {

    suspend fun getAllCharacters(next : String?):Response<Characters>

    suspend fun getSingleCharacter(id : String):Response<Character>

    suspend fun getHumanSpecies(specie : String):Response<Characters>

    suspend fun getAlienSpecies(specie : String):Response<Characters>

    suspend fun getAnimalSpecies(specie : String):Response<Characters>

    suspend fun getUnknownSpecies(specie : String):Response<Characters>

    suspend fun getCharacterBySearchingName(name : String):Response<Characters>
}