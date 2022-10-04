package com.example.rickmorty.feature.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getHumanSpecieUseCase : GetHumanSpeciesUsecase,
    private val getAlienSpecieUseCase : GetAlienSpeciesUsecase,
    private val getAnimalSpeciesUseCase: GetAnimalSpeciesUsecase,
    private val getUnknownSpeciesUseCase: GetUnknownSpeciesUsecase,
    private val getAllCharacterUseCase: GetAllCharacterUsecase
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

    private var error = MutableLiveData<String>()
    val errorData : LiveData<String>
        get() = error

     var isLoading : Boolean = false


    sealed class BaseState{
        data class Loading(var isLoad: Boolean) : BaseState()
        data class Error(var errorMessage : String): BaseState()
        data class Success(var data : List<Character>):BaseState()
    }

    // human
    fun getCharacterByHumanSpecies(){
        viewModelScope.launch{
            val result = getHumanSpecieUseCase.getCharacterSpecies(RMKey.TYPE_HUMAN)
            result.let {
                humans.postValue(Resource.Success(it.data))
            }
        }
    }

    // alien
    fun getCharacterByAlienSpecies(){
        viewModelScope.launch{
            val result = getAlienSpecieUseCase.getCharacterSpecies(RMKey.TYPE_ALIEN)
            result.let {
                aliens.postValue(Resource.Success(it.data))
            }
        }
    }

    // animal
    fun getCharacterByAnimalSpecies(){
        viewModelScope.launch{
            val result = getAnimalSpeciesUseCase.getCharacterSpecies(RMKey.TYPE_ANIMAL)
            result.let {
                animals.postValue(Resource.Success(it.data))
            }
        }
    }

    // unknown
    fun getCharacterByUnknownSpecies(){
        viewModelScope.launch{
            val result = getUnknownSpeciesUseCase.getCharacterSpecies(RMKey.TYPE_UNKNOWN)
            result.let {
                unknown.postValue(Resource.Success(it.data))
            }
        }
    }

    // all
    fun getCharacterByAllSpecies(next : String? = null){
        viewModelScope.launch{
            isLoading = true
            all.postValue(Resource.Loading())
                val output = getAllCharacterUseCase.getCharacterAllSpecies(next)
                isLoading = false
                when(output){
                    is Resource.Success ->{

                    }
                    is Resource.Error ->{
                        error.postValue(output.message.toString())
                    }
                }
                all.postValue(Resource.Success(output.data))

        }
    }


}