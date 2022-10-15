package com.example.rickmorty.app.data.remote.datasourceImpl

import com.example.rickmorty.app.data.model.Episodes
import com.example.rickmorty.app.data.remote.ServiceAPI
import com.example.rickmorty.app.data.remote.datasource.EpisodeRemoteDataSource
import retrofit2.Response

class EpisodeRemoteDataSourceImpl(private val serviceAPI: ServiceAPI) : EpisodeRemoteDataSource {

    override suspend fun getAllEpisode(nextPage : String?): Response<Episodes> {
            return serviceAPI.getAllEpisode(nextPage)
    }
}