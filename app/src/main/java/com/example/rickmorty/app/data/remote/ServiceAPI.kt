package com.example.rickmorty.app.data.remote

import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.app.data.model.Locations
import com.example.rickmorty.app.data.utils.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceAPI {

    @GET("character")
    suspend fun getAllCharacter(
        @Query("page")page : String?
    ):Response<Characters>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id : String):Response<Character>

    @GET("character")
    suspend fun getCharacterBySpecie(@Query("species") species : String):Response<Characters>

    @GET("location")
    suspend fun getAllLocation(@Query("page")page : String?):Response<Locations>
}