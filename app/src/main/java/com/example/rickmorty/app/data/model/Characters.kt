package com.example.rickmorty.app.data.model

import java.io.Serializable

data class Characters(
    val info: Info,
    val results: ArrayList<Character>
):Serializable