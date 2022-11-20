package com.example.rickmorty.app.domain.usecase

import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterBySearchingUsecase(
    private val repository: CharacterRepository
) {


    suspend fun getCharacterBySearchName(name: String): Resource<Characters> {
        return repository.getCharacterBySearchName(name)
    }

//    use flow
//    suspend fun getCharacterBySearchName(name: String): Flow<Resource<Characters>> {
//        return repository.getCharacterBySearchName(name)
//    }
}