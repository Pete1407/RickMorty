package com.example.rickmorty.app.domain.datasource

import com.example.rickmorty.app.data.model.Characters
import retrofit2.Response

interface CharacterRemoteDataSource {

    suspend fun getAllCharacters():Response<Characters>
}