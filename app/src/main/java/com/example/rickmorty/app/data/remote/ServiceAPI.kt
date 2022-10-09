package com.example.rickmorty.app.data.remote

import com.example.rickmorty.app.data.model.*
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

    @GET("location/{id}")
    suspend fun getSpecificLocation(@Path("id") id : String):Response<Location>

    @GET("episode")
    suspend fun getAllEpisode():Response<Episodes>
}