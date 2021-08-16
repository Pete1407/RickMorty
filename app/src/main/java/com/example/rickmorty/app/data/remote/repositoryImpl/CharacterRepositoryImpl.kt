package com.example.rickmorty.app.data.remote.repositoryImpl

import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.remote.datasource.CharacterRemoteDataSource
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.repository.CharacterRepository
import retrofit2.Response

class CharacterRepositoryImpl(
    private val remoteDataSource: CharacterRemoteDataSource
): CharacterRepository {
    override suspend fun getAllCharacter(): Resource<Characters> {
        return convertResponseToResource(remoteDataSource.getAllCharacters())
    }

    override suspend fun getSingleCharacter(): Resource<Character> {
        TODO("Not yet implemented")
    }

    override suspend fun getMultipleCharacter(): Resource<Characters> {
        TODO("Not yet implemented")
    }

    private fun convertResponseToResource(data : Response<Characters>?):Resource<Characters>{
        data?.let {
            if(it.isSuccessful){
                val body = it.body()
                return Resource.Success(body,"Success")
            }
        }
        return Resource.Error(data?.message(),null)
    }
}