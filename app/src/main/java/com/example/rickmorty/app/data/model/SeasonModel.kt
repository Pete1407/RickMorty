package com.example.rickmorty.app.data.model

data class SeasonModel(
    val seasonText: String? = null,
    val episodes: List<Episode> = arrayListOf()
)
