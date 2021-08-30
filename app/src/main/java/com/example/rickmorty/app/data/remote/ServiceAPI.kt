package com.example.rickmorty.app.data.remote

import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceAPI {

    @GET("character")
    suspend fun getAllCharacter():Response<Characters>

    @GET("character/{id}")
    suspend fun getCharacter(@Query("id") id : String):Response<Character>
}