package com.example.rickmorty.feature.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.app.domain.usecase.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterViewModelFactory @Inject constructor(
    private val getHumanSpeciesUsecase: GetHumanSpeciesUsecase,
    private val getAlienSpecieUsecase : GetAlienSpeciesUsecase,
    private val getAnimalSpeciesUsecase: GetAnimalSpeciesUsecase,
    private val getUnknownSpeciesUsecase: GetUnknownSpeciesUsecase,
    private val getAllCharacterUsecase: GetAllCharacterUsecase,
    private val getCharacterBySearchingUsecase: GetCharacterBySearchingUsecase,
    private val getSingleCharacterUsecase: GetSingleCharacterUsecase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(
                getHumanSpeciesUsecase,
                getAlienSpecieUsecase,
                getAnimalSpeciesUsecase,
                getUnknownSpeciesUsecase,
                getAllCharacterUsecase,
                getCharacterBySearchingUsecase,
                getSingleCharacterUsecase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}