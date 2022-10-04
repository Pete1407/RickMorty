package com.example.rickmorty.app.domain.repository

import com.example.rickmorty.app.data.model.Episodes
import com.example.rickmorty.app.data.utils.Resource

interface EpisodeRepository {

    suspend fun getAllEpisode():Resource<Episodes>
}