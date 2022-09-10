package com.example.rickmorty.feature.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.app.data.model.Locations
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.usecase.GetAllLocationUsecase
import kotlinx.coroutines.launch

class LocationViewModel(
    private val getLocationsUseCase : GetAllLocationUsecase
) : ViewModel() {

    private var location = MutableLiveData<Resource<Locations>>()
    val locationData : LiveData<Resource<Locations>>
        get() = location

    fun getAllLocation(){
        viewModelScope.launch {
            val result = getLocationsUseCase.getAllLocation()
            location.postValue(result)
        }
    }
}