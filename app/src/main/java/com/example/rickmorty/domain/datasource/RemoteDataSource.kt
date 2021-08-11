package com.example.rickmorty.domain.datasource

import com.example.rickmorty.data.model.Characters
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getAllCharacters():Response<Characters>
}