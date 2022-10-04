package com.example.rickmorty.app.data.remote.datasource

import com.example.rickmorty.app.data.model.Episodes
import retrofit2.Response

interface EpisodeRemoteDataSource {

    suspend fun getAllEpisode():Response<Episodes>
}