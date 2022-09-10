package com.example.rickmorty.app.data.remote.datasourceImpl

import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.app.data.model.Locations
import com.example.rickmorty.app.data.remote.ServiceAPI
import com.example.rickmorty.app.data.remote.datasource.LocationRemoteDataSource
import retrofit2.Response

class LocationRemoteDataSourceImpl(private val apiService : ServiceAPI) : LocationRemoteDataSource {

    override suspend fun getAllLocation(): Response<Locations> {
        return apiService.getAllLocation()
    }
}