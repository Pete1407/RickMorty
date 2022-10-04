package com.example.rickmorty.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Episode(
    @field:Json(name = "air_date")
    val air_date: String,
    @field:Json(name = "characters")
    val characters: List<String>,
    @field:Json(name = "created")
    val created: String,
    @field:Json(name = "episode")
    val episode: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url")
    val url: String
)
