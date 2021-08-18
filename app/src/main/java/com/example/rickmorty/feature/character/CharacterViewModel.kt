package com.example.rickmorty.feature.character

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.app.base.RmKey
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.usecase.GetAllCharacterUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharactersUseCase: GetAllCharacterUsecase
) : ViewModel() {

    var characters = MutableLiveData<Resource<Characters>>()

    fun getCharactersData(){
        characters.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val result = getCharactersUseCase.execute().data
            result?.let {
                if(it.results.size > 0){
                    Log.i("result","result come...")
                }else{
                    Log.i("result","result not come!!!!")
                }
            }
            characters.postValue(Resource.Success(result,RmKey.SUCCESS))
        }
    }
}