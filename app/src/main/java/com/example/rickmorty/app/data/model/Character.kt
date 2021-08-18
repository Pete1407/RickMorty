package com.example.rickmorty.app.data.model

import java.io.Serializable

data class Character(
    val created: String,
    val episode: ArrayList<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
):Serializable