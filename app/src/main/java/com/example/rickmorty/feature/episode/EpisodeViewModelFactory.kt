package com.example.rickmorty.feature.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.app.domain.usecase.GetAllEpisodeUsecase

class EpisodeViewModelFactory(
    private val getAllEpisodeUsecase: GetAllEpisodeUsecase
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EpisodeViewModel::class.java)){
            return EpisodeViewModel(getAllEpisodeUsecase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}