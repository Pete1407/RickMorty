package com.example.rickmorty.app.data.remote

import com.example.rickmorty.app.data.model.Characters
import retrofit2.Response
import retrofit2.http.GET

interface ServiceAPI {

    @GET("character")
    fun getAllCharacter():Response<Characters>


}