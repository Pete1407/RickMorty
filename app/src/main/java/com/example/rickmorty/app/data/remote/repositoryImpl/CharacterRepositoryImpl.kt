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

    // main function
    override suspend fun getAllCharacter(): Resource<Characters> {
        return convertResponseToResource(remoteDataSource.getAllCharacters())
    }

    // main function
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

    override suspend fun getCharacterByHuman(specie: String): Resource<Characters> {
        val output = remoteDataSource.getHumanSpecies(specie)
        if(output.isSuccessful) {
            output.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(output.message(),null)
    }

    override suspend fun getCharacterByAlien(specie: String): Resource<Characters> {
        val output = remoteDataSource.getAlienSpecies(specie)
        if(output.isSuccessful) {
            output.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(output.message(),null)
    }

    override suspend fun getCharacterByAnimal(specie: String): Resource<Characters> {
        val output = remoteDataSource.getAnimalSpecies(specie)
        if(output.isSuccessful) {
            output.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(output.message(),null)
    }

    override suspend fun getCharacterByUnknown(specie: String): Resource<Characters> {
        val output = remoteDataSource.getUnknownSpecies(specie)
        if(output.isSuccessful) {
            output.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(output.message(),null)
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

}