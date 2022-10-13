package com.example.rickmorty.feature.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.app.data.model.Episodes
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.usecase.GetAllEpisodeUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val getAllEpisodeUsecase: GetAllEpisodeUsecase
) : ViewModel() {

    private var epsiodes = MutableLiveData<Resource<Episodes>>()
    val episodesData : LiveData<Resource<Episodes>>
        get() = epsiodes

    fun getAllEpisode(){
        epsiodes.postValue(Resource.Loading())
        viewModelScope.launch {
            val result = getAllEpisodeUsecase.getAllEpisode()
            epsiodes.postValue(Resource.Success(result.data))
        }
    }


}