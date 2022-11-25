package com.example.rickmorty.app.domain.repository

import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.model.Info
import com.example.rickmorty.app.data.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    suspend fun getAllCharacter(next : String?):Resource<Characters>

    suspend fun getSingleCharacter(id : String):Resource<Character>

    suspend fun getCharacterByHuman(specie : String):Resource<Characters>

    suspend fun getCharacterByAlien(specie: String):Resource<Characters>

    suspend fun getCharacterByAnimal(specie: String):Resource<Characters>

    suspend fun getCharacterByUnknown(specie : String):Resource<Characters>

    suspend fun getCharacterBySearchName(name : String):Resource<Characters>

}