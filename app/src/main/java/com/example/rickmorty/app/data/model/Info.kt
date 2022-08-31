package com.example.rickmorty.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Info(
    @field:Json(name = "count")
    val count: Int? = null,
    @field:Json(name = "next")
    val next: String? = null,
    @field:Json(name = "pages")
    val pages: Int? = null,
    @field:Json(name = "prev")
    val prev: String? = null
)