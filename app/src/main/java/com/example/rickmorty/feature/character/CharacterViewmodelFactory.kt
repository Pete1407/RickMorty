package com.example.rickmorty.feature.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.app.domain.usecase.GetAllCharacterUsecase

class CharacterViewModelFactory(
    private val allCharacterUseCase: GetAllCharacterUsecase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CharacterViewModel::class.java)){
            return CharacterViewModel(allCharacterUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}