package com.example.rickmorty.feature.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.app.domain.usecase.*

class CharacterViewModelFactory(
    private val getHumanSpeciesUsecase: GetHumanSpeciesUsecase,
    private val getAlienSpecieUsecase : GetAlienSpeciesUsecase,
    private val getAnimalSpeciesUsecase: GetAnimalSpeciesUsecase,
    private val getUnknownSpeciesUsecase: GetUnknownSpeciesUsecase,
    private val getAllCharacterUsecase: GetAllCharacterUsecase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(
                getHumanSpeciesUsecase,
                getAlienSpecieUsecase,
                getAnimalSpeciesUsecase,
                getUnknownSpeciesUsecase,
                getAllCharacterUsecase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}