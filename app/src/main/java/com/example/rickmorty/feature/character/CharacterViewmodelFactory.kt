package com.example.rickmorty.feature.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.app.domain.usecase.GetAllCharacterUsecase
import com.example.rickmorty.app.domain.usecase.GetHumanSpeciesUsecase
import com.example.rickmorty.app.domain.usecase.GetSingleCharacterUsecase

class CharacterViewModelFactory(
    private val allCharacterUseCase: GetAllCharacterUsecase,
    private val getSingleCharacterUsecase: GetSingleCharacterUsecase,
    private val getHumanSpeciesUsecase: GetHumanSpeciesUsecase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(allCharacterUseCase, getSingleCharacterUsecase,getHumanSpeciesUsecase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}