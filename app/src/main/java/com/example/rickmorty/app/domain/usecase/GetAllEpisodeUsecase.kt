package com.example.rickmorty.app.domain.usecase

import com.example.rickmorty.app.data.model.Episodes
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.domain.repository.EpisodeRepository

class GetAllEpisodeUsecase(private val repository: EpisodeRepository) {

    suspend fun getAllEpisode(nextPage : String?):Resource<Episodes>{
        return repository.getAllEpisode(nextPage)
    }
}