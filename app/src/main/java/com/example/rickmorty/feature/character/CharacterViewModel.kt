package com.example.rickmorty.feature.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.model.Info
import com.example.rickmorty.app.data.utils.ApiException
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CharacterViewModel(
    private val getHumanSpecieUsecase : GetHumanSpeciesUsecase,
    private val getAlienSpecieUsecase : GetAlienSpeciesUsecase,
    private val getAnimalSpeciesUsecase: GetAnimalSpeciesUsecase,
    private val getUnknownSpeciesUsecase: GetUnknownSpeciesUsecase,
    private val getAllCharacterUsecase: GetAllCharacterUsecase
) : ViewModel() {

    /*
    1. Human
    2. Alien
    3. Animal
    4. Unknown
    5. All and load more
*/

    //private var isLoading = MutableLiveData<Boolean>()

    private var humans = MutableLiveData<Resource<Characters>>()
    val humanData : LiveData<Resource<Characters>>
    get() = humans

    private var aliens = MutableLiveData<Resource<Characters>>()
    val alienData : LiveData<Resource<Characters>>
        get() = aliens

    private var animals = MutableLiveData<Resource<Characters>>()
    val animalData : LiveData<Resource<Characters>>
        get() = animals

    private var unknown = MutableLiveData<Resource<Characters>>()
    val unknownData : LiveData<Resource<Characters>>
        get() = unknown

    private var all = MutableLiveData<Resource<Characters>>()
    val allData : LiveData<Resource<Characters>>
        get() = all

    var error = MutableLiveData<String?>()

     var isLoading : Boolean = false


    sealed class BaseState{
        data class Loading(var isLoad: Boolean) : BaseState()
        data class Error(var errorMessage : String): BaseState()
        data class Success(var data : List<Character>):BaseState()
    }

    // human
    fun getCharacterByHumanSpecies(){
        viewModelScope.launch {
            val result = getHumanSpecieUsecase.getCharacterSpecies(RMKey.TYPE_HUMAN)
            result.let {
                humans.postValue(Resource.Success(it.data))
            }
        }
    }

    // alien
    fun getCharacterByAlienSpecies(){
        viewModelScope.launch {
            val result = getAlienSpecieUsecase.getCharacterSpecies(RMKey.TYPE_ALIEN)
            result.let {
                aliens.postValue(Resource.Success(it.data))
            }
        }
    }

    // animal
    fun getCharacterByAnimalSpecies(){
        viewModelScope.launch {
            val result = getAnimalSpeciesUsecase.getCharacterSpecies(RMKey.TYPE_ANIMAL)
            result.let {
                animals.postValue(Resource.Success(it.data))
            }
        }
    }

    // unknown
    fun getCharacterByUnknownSpecies(){
        viewModelScope.launch {
            val result = getUnknownSpeciesUsecase.getCharacterSpecies(RMKey.TYPE_UNKNOWN)
            result.let {
                unknown.postValue(Resource.Success(it.data))
            }
        }
    }

    // all
    fun getCharacterByAllSpecies(next : String? = null){
        viewModelScope.launch {
            isLoading = true
            all.postValue(Resource.Loading())
            try {
                val output = getAllCharacterUsecase.getCharacterAllSpecies(next)
                isLoading = false
                all.postValue(Resource.Success(output.data))
            }
            catch (exception : Exception){
                error.postValue(exception.message)
            }
        }
    }


}