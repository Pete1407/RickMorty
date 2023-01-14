package com.example.rickmorty.feature.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.app.domain.usecase.GetAllEpisodeUsecase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EpisodeViewModelFactory @Inject constructor(
    private val getAllEpisodeUsecase: GetAllEpisodeUsecase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EpisodeViewModel::class.java)){
            return EpisodeViewModel(getAllEpisodeUsecase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}