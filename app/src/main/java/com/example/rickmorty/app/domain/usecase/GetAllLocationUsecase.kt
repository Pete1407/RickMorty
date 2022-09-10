package com.example.rickmorty.app.domain.usecase

import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.app.data.model.Locations
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.repository.LocationRepository

class GetAllLocationUsecase(private val repository: LocationRepository) {

    suspend fun getAllLocation():Resource<Locations>{
        return repository.getAllLocation()
    }
}