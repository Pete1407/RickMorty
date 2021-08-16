package com.example.rickmorty.feature.character

import androidx.lifecycle.ViewModel
import com.example.rickmorty.app.domain.usecase.GetAllCharacterUsecase

class CharacterViewModel(
    private val getCharactersUseCase: GetAllCharacterUsecase
) : ViewModel() {


}