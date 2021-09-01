package com.example.rickmorty.app.data.remote.repositoryImpl

import android.util.Log
import com.example.rickmorty.app.base.RmKey
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

    private fun convertResponseToResource(response : Response<Characters>):Resource<Characters>{
        val success = response.isSuccessful
        if(success){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message(),null)

    }

    override suspend fun getSingleCharacter(id : String): Resource<Character> {
       val output = remoteDataSource.getSingleCharacter(id)
       if(output.isSuccessful) {
            output.body()?.let {
                return Resource.Success(it)
            }
       }
       return Resource.Error(output.message(),null)
    }

    override suspend fun getMultipleCharacter(): Resource<Characters> {
        TODO("Not yet implemented")
    }

}