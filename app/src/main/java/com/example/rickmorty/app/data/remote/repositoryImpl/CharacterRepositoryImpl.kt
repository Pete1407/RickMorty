package com.example.rickmorty.app.data.remote.repositoryImpl

import android.util.Log
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.model.ErrorResponse
import com.example.rickmorty.app.data.model.Info
import com.example.rickmorty.app.data.remote.datasource.CharacterRemoteDataSource
import com.example.rickmorty.app.data.utils.ApiException
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.repository.CharacterRepository
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import kotlin.Exception
import kotlin.jvm.Throws
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.FlexibleTypeDeserializer

class CharacterRepositoryImpl(
    private val remoteDataSource: CharacterRemoteDataSource
): CharacterRepository {

    override suspend fun getAllCharacter(next : String?): Resource<Characters> {
        return safeApiCall{
            remoteDataSource.getAllCharacters(next)
        }
    }

    override suspend fun getSingleCharacter(id : String): Resource<Character> {
       return safeApiCall {
           remoteDataSource.getSingleCharacter(id)
       }
    }

    override suspend fun getCharacterByHuman(specie: String): Resource<Characters> {
        val result = remoteDataSource.getHumanSpecies(specie)
        return if(result.isSuccessful && result.body()!= null){
            Resource.Success(result.body())
        }else if(result.errorBody()!= null){
            val errorObject = JSONObject(result.errorBody()!!.charStream().readText())
            Resource.Error(errorObject.getString("error"))
        }else{
            Resource.Error("Something Went Wrong!!")
        }
    }

    override suspend fun getCharacterByAlien(specie: String): Resource<Characters> {
        val result = remoteDataSource.getAlienSpecies(specie)
        return if(result.isSuccessful && result.body()!= null){
            Resource.Success(result.body())
        }else if(result.errorBody()!= null){
            val errorObject = JSONObject(result.errorBody()!!.charStream().readText())
            Resource.Error(errorObject.getString("error"))
        }else{
            Resource.Error("Something Went Wrong!!")
        }
    }

    override suspend fun getCharacterByAnimal(specie: String): Resource<Characters> {
        val result = remoteDataSource.getAnimalSpecies(specie)
        return if(result.isSuccessful && result.body()!= null){
            Resource.Success(result.body())
        }else if(result.errorBody()!= null){
            val errorObject = JSONObject(result.errorBody()!!.charStream().readText())
            Resource.Error(errorObject.getString("error"))
        }else{
            Resource.Error("Something Went Wrong!!")
        }
    }

    override suspend fun getCharacterByUnknown(specie: String): Resource<Characters> {
        val result = remoteDataSource.getUnknownSpecies(specie)
        return if(result.isSuccessful && result.body()!= null){
            Resource.Success(result.body())
        }else if(result.errorBody()!= null){
            val errorObject = JSONObject(result.errorBody()!!.charStream().readText())
            Resource.Error(errorObject.getString("error"))
        }else{
            Resource.Error("Something Went Wrong!!")
        }
    }

    override suspend fun getCharacterBySearchName(name: String): Resource<Characters> {
        val result = remoteDataSource.getCharacterBySearchingName(name)
        return if(result.isSuccessful && result.body()!= null){
            Resource.Success(result.body())
        }else if(result.errorBody()!= null){
            val errorObject = JSONObject(result.errorBody()!!.charStream().readText())
            Resource.Error(errorObject.getString("error"))
        }else{
            Resource.Error("Something Went Wrong!!")
        }
    }


    // use flow
//    override suspend fun getCharacterBySearchName(name: String): Flow<Resource<Characters>> {
//        return flow<Resource<Characters>> {
//            val result = remoteDataSource.getCharacterBySearchingName(name)
//
//            if(result.isSuccessful && result.body()!= null){
//                emit(Resource.Success(result.body()))
//            }
//            else if(result.errorBody()!= null){
//                val errorObj = JSONObject(result.errorBody()!!.charStream().readText())
//                emit(Resource.Error(errorObj.getString("error")))
//            }
//            else{
//                emit(Resource.Error("Something Went Wrong!!"))
//            }
//        }.flowOn(Dispatchers.IO)
//    }

//    private fun convertResponseToResource(response : Response<Characters>):Resource<Characters> {
//        if (response.isSuccessful) {
//            val result = response.body()
//            return Resource.Success(result)
//        }
//        val errorMsg = response.errorBody()
//        return Resource.Error(errorMsg.toString())
//
//    }

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
                    return Resource.Error(msg = errorResponse?.failureMessage ?: "Something went wrong")
                    //throw ApiException(errorResponse.toString())
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