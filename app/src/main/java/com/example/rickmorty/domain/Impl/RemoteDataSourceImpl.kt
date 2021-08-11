package com.example.rickmorty.domain.Impl

import com.example.rickmorty.data.model.Characters
import com.example.rickmorty.domain.datasource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(

) : RemoteDataSource{

    fun getChatacters(){

    }

    override suspend fun getAllCharacters(): Response<Characters> {
        TODO("Not yet implemented")
    }

}