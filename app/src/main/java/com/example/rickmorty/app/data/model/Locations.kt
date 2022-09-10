package com.example.rickmorty.app.data.model

import com.squareup.moshi.Json

data class Locations(
    @field:Json(name = "info")
    val info: Info,
    @field:Json(name = "results")
    val results: List<Location>
)