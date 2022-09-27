package com.example.rickmorty.app.data.remote.datasource

import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.app.data.model.Locations
import com.example.rickmorty.app.data.utils.Resource
import retrofit2.Response

interface LocationRemoteDataSource {

    suspend fun getAllLocation(nextPage : String):Response<Locations>

    suspend fun getSingleLocation(id : String):Response<Location>
}