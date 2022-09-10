package com.example.rickmorty.feature.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.app.domain.usecase.GetAllLocationUsecase
import com.example.rickmorty.feature.character.CharacterViewModel

class LocationViewModelFactory(private val getAllLocationUsecase: GetAllLocationUsecase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationViewModel::class.java)) {
            return LocationViewModel(getAllLocationUsecase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}