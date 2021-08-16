package com.example.rickmorty.feature.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.domain.usecase.GetAllCharacterUsecase
import java.util.ArrayList

class CharacterViewModel(
    private val getCharactersUseCase: GetAllCharacterUsecase
) : ViewModel() {

    private var characterLists = MutableLiveData<ArrayList<Character>>()
    val characterLiveData : LiveData<ArrayList<Character>>
    get() = characterLists
}