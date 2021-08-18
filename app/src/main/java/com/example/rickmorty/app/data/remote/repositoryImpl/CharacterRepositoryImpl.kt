package com.example.rickmorty.app.data.remote.repositoryImpl

import android.util.Log
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
            if(data?.isSuccessful == true){
                Log.i("result","-- GetAllCharacter --")
                Log.i("result","${data.body()}")
                val body = data.body()
                return Resource.Success(body,"Success")
            }else{
                Log.i("result","isNotSuccessful")
            }
        return Resource.Error(data?.message(),null)
    }
}