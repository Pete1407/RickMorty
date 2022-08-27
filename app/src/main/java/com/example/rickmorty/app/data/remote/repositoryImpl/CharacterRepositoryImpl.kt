package com.example.rickmorty.app.data.remote.repositoryImpl

import android.util.Log
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.model.ErrorResponse
import com.example.rickmorty.app.data.remote.datasource.CharacterRemoteDataSource
import com.example.rickmorty.app.data.utils.ApiException
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.repository.CharacterRepository
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.Exception
import kotlin.jvm.Throws
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer

class CharacterRepositoryImpl(
    private val remoteDataSource: CharacterRemoteDataSource
): CharacterRepository {

    override suspend fun getAllCharacter(): Resource<Characters> {
        return safeApiCall{
            remoteDataSource.getAllCharacters()
        }
    }

    override suspend fun getSingleCharacter(id : String): Resource<Character> {
       val output = remoteDataSource.getSingleCharacter(id)
       if(output.isSuccessful) {
            output.body()?.let {
                return Resource.Success(it)
            }
       }
       return Resource.Error(output.message())
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
        return Resource.Error(output.message())
    }

    override suspend fun getCharacterByAlien(specie: String): Resource<Characters> {
        val output = remoteDataSource.getAlienSpecies(specie)
        if(output.isSuccessful) {
            output.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(output.message())
    }

    override suspend fun getCharacterByAnimal(specie: String): Resource<Characters> {
        val output = remoteDataSource.getAnimalSpecies(specie)
        if(output.isSuccessful) {
            output.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(output.message())
    }

    override suspend fun getCharacterByUnknown(specie: String): Resource<Characters> {
        val output = remoteDataSource.getUnknownSpecies(specie)
        if(output.isSuccessful) {
            output.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(output.message())
    }

    private fun convertResponseToResource(response : Response<Characters>):Resource<Characters> {
        if (response.isSuccessful) {
            val result = response.body()
            return Resource.Success(result)
        }
        val errorMsg = response.errorBody()
        return Resource.Error(errorMsg.toString())

    }

    // we'll use this function in all
    // repos to handle api errors.
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {

        // Returning api response
        // wrapped in Resource class

                // Here we are calling api lambda
                // function that will return response
                // wrapped in Retrofit's Response class
                val response: Response<T> = apiToBeCalled()
                if (response.isSuccessful) {
                    // In case of success response we
                    // are returning Resource.Success object
                    // by passing our data in it.
                    //Resource.Success(result = response.body())
                    return Resource.Success(response.body()!!)
                } else {
                    // parsing api's own custom json error
                    // response in ExampleErrorResponse pojo
                    val errorResponse: ErrorResponse? = convertErrorBody(response.errorBody())
                    // Simply returning api's own failure message
                    //Resource.Error(msg = errorResponse?.failureMessage ?: "Something went wrong")
                    throw ApiException(errorResponse.toString())
                }
    }

    // If you don't wanna handle api's own
    // custom error response then ignore this function
    private fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
        return try {
            errorBody?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }
}