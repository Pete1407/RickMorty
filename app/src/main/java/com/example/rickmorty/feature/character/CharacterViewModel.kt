package com.example.rickmorty.feature.character

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
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

    private var humans = MutableLiveData<BaseState>()
    val humanData : LiveData<BaseState>
    get() = humans

    private var aliens = MutableLiveData<BaseState>()
    val alienData : LiveData<BaseState>
        get() = aliens

    private var animals = MutableLiveData<BaseState>()
    val animalData : LiveData<BaseState>
        get() = animals

    private var unknown = MutableLiveData<BaseState>()
    val unknownData : LiveData<BaseState>
        get() = unknown

    private var all = MutableLiveData<Resource<Characters>>()
    val allData : LiveData<Resource<Characters>>
        get() = all

    var error = MutableLiveData<String?>()

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
                humans.postValue(BaseState.Success(it.data!!.results))
            }
        }
    }

    // alien
    fun getCharacterByAlienSpecies(){
        viewModelScope.launch {
            val result = getAlienSpecieUsecase.getCharacterSpecies(RMKey.TYPE_ALIEN)
            result.let {
                aliens.postValue(BaseState.Success(it.data!!.results))
            }
        }
    }

    // animal
    fun getCharacterByAnimalSpecies(){
        viewModelScope.launch {
            val result = getAnimalSpeciesUsecase.getCharacterSpecies(RMKey.TYPE_ANIMAL)
            result.let {
                animals.postValue(BaseState.Success(it.data!!.results))
            }
        }
    }

    // unknown
    fun getCharacterByUnknownSpecies(){
        viewModelScope.launch {
            val result = getUnknownSpeciesUsecase.getCharacterSpecies(RMKey.TYPE_UNKNOWN)
            result.let {
                unknown.postValue(BaseState.Success(it.data!!.results))
            }
        }
    }

    // all
    fun getCharacterByAllSpecies(){
        viewModelScope.launch {
            all.postValue(Resource.Loading())
            try {
                val output = getAllCharacterUsecase.getCharacterAllSpecies()
                all.postValue(Resource.Success(output.data))
            }
            catch (exception : ApiException){
                error.postValue(exception.message)
            }
            catch (exception : UnknownHostException){
                error.postValue(exception.message)
            }
            catch (exception : SocketTimeoutException){
                error.postValue(exception.message)
            }
            catch (exception : ConnectException){
                error.postValue(exception.message)
            }
            catch (exception : Exception){
                error.postValue(exception.message)
            }
        }
    }


}