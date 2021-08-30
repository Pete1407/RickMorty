package com.example.rickmorty.app.domain.usecase

import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.repository.CharacterRepository

class GetSingleCharacterUsecase(private val repository : CharacterRepository) {

    suspend fun execute():Resource<Character>{
        return repository.getSingleCharacter()
    }
}