package com.example.rickmorty.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Episodes(
    @field:Json(name = "info")
    val info: Info,
    @field:Json(name = "results")
    val results: List<Episode>
)