package com.example.rickmorty.app.data.remote.datasource

import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import retrofit2.Response

interface CharacterRemoteDataSource {

    suspend fun getAllCharacters():Response<Characters>

    suspend fun getSingleCharacter(id : String):Response<Character>

    suspend fun getHumanSpecies(specie : String): Response<Characters>
}