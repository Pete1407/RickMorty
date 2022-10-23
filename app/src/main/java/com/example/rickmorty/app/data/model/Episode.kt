package com.example.rickmorty.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Episode(
    @field:Json(name = "air_date")
    var air_date: String,
    @field:Json(name = "characters")
    var characters: List<String>,
    @field:Json(name = "created")
    var created: String,
    @field:Json(name = "episode")
    var episode: String,
    @field:Json(name = "id")
    var id: Int,
    @field:Json(name = "name")
    var name: String,
    @field:Json(name = "url")
    var url: String,
    @field:Json(name = "episodeText")
    var episodeText : String? = ""
)
