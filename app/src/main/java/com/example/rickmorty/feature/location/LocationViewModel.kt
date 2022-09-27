package com.example.rickmorty.feature.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.app.data.model.Locations
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.usecase.GetAllLocationUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.rickmorty.app.domain.usecase.GetSingleLocationUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationsUseCase : GetAllLocationUsecase,
    private val getSingleLocationUsecase: GetSingleLocationUsecase
) : ViewModel() {

    private var location = MutableLiveData<Resource<Locations>>()
    val locationData : LiveData<Resource<Locations>>
        get() = location

    private var aLocation = MutableLiveData<Resource<Location>>()
    val aLocationData : LiveData<Resource<Location>>
        get() = aLocation

    var isLoading : Boolean = false

    fun getAllLocation(nextPage : String? = null){
        isLoading = true
        location.postValue(Resource.Loading())
        viewModelScope.launch {
            val result = getLocationsUseCase.getAllLocation(nextPage.toString())
            isLoading = false
            location.postValue(result)
        }
    }

    fun getSpecificLocation(id : String){
        aLocation.postValue(Resource.Loading())
        viewModelScope.launch {
            val result = getSingleLocationUsecase.getSpecificLocation(id)
            aLocation.postValue(result)
        }
    }
}