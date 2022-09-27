package com.example.rickmorty.app.domain.repository

import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.app.data.model.Locations
import com.example.rickmorty.app.data.utils.Resource
import retrofit2.Response

interface LocationRepository {

    suspend fun getAllLocation(nextPage : String):Resource<Locations>

    suspend fun getSingleLocation(id : String):Resource<Location>
}