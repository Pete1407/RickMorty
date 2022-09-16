package com.example.rickmorty.app.data.model

import android.net.Uri
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Characters(
    @field:Json(name = "info")
    val info: Info,
    @field:Json(name = "results")
    val results: List<Character>
)