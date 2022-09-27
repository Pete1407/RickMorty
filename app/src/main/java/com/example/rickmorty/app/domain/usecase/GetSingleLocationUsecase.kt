package com.example.rickmorty.app.domain.usecase

import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.repository.LocationRepository

class GetSingleLocationUsecase(private val repository: LocationRepository) {

    suspend fun getSpecificLocation(id : String):Resource<Location>{
        return repository.getSingleLocation(id)
    }
}