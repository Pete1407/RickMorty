package com.example.rickmorty.feature.character

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.app.base.RmKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.usecase.GetAllCharacterUsecase
import com.example.rickmorty.app.domain.usecase.GetSingleCharacterUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharactersUseCase: GetAllCharacterUsecase,
    private val getSingleCharacterUseCase : GetSingleCharacterUsecase
) : ViewModel() {

    var character = MutableLiveData<Resource<Character>>()
    var characters = MutableLiveData<Resource<Characters>>()
    var list = ArrayList<Character>()

    fun getCharactersData(){
        characters.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCharactersUseCase.execute().data
            result?.let {
                characters.postValue(Resource.Success(it,RmKey.SUCCESS))
            }?:kotlin.run {
                Log.d("!!!  debug","result is null.  !!!")
            }
        }
    }

    fun getSingleCharacter(id : String){
        character.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val result = getSingleCharacterUseCase.execute(id)
            result?.let {
                character.postValue(it)
            }
        }
    }
}