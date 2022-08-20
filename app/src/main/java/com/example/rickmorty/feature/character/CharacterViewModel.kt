package com.example.rickmorty.feature.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.usecase.GetAllCharacterUsecase
import com.example.rickmorty.app.domain.usecase.GetHumanSpeciesUsecase
import com.example.rickmorty.app.domain.usecase.GetSingleCharacterUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharactersUseCase: GetAllCharacterUsecase,
    private val getSingleCharacterUseCase : GetSingleCharacterUsecase,
    private val getHumanSpecieUsecase : GetHumanSpeciesUsecase
) : ViewModel() {

    var character = MutableLiveData<Resource<Character>>()
    var characters = MutableLiveData<Resource<Characters>>()
    var list = ArrayList<Character>()

    private var uiState = MutableLiveData<BaseState>()
    val _uiState : LiveData<BaseState>
    get() = uiState

    sealed class BaseState{
        data class Loading(var isLoad: Boolean) : BaseState()
        data class Error(var error : String): BaseState()
        data class Success(var data : List<Character>):BaseState()
    }

/*
    1. Human
    2. Alien
    3. Animal
    4. Unknown
    5. All and load more
*/

    fun getCharacterBySpecies(specie : String = ""){
        viewModelScope.launch {
            val result = getHumanSpecieUsecase.getCharacterSpecies(specie)
            result.let {
                uiState.postValue(BaseState.Success(it.data!!.results))
            }
        }
    }

//    fun getSingleCharacter(id : String){
//        character.postValue(Resource.Loading())
//        viewModelScope.launch(Dispatchers.IO) {
//            val result = getSingleCharacterUseCase.execute(id)
//            result.let {
//                character.postValue(it)
//            }
//        }
//    }
}