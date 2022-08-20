package com.example.rickmorty.app.domain.repository

import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.utils.Resource

interface CharacterRepository {

    suspend fun getAllCharacter():Resource<Characters>

    suspend fun getSingleCharacter(id : String):Resource<Character>

    suspend fun getMultipleCharacter():Resource<Characters>

    suspend fun getCharacterBySpecies(specie : String):Resource<Characters>

}