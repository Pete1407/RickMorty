package com.example.rickmorty.app.domain.usecase

import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.repository.CharacterRepository

class GetAllCharacterUsecase(private val repository: CharacterRepository) {

    suspend fun execute():Resource<Characters>{
        return repository.getAllCharacter()
    }
}