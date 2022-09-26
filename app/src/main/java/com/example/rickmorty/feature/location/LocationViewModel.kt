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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationsUseCase : GetAllLocationUsecase
) : ViewModel() {

    private var location = MutableLiveData<Resource<Locations>>()
    val locationData : LiveData<Resource<Locations>>
        get() = location

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
}