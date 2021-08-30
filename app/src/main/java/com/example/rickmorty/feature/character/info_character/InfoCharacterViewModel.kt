package com.example.rickmorty.feature.character.info_character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.app.domain.usecase.GetSingleCharacterUsecase
import kotlinx.coroutines.launch

class InfoCharacterViewModel(
    private val getCharacterUseCase : GetSingleCharacterUsecase
) : ViewModel() {

    fun getCharacter(id : String){
        viewModelScope.launch {

        }
    }
}