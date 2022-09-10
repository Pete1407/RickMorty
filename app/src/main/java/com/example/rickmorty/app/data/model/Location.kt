package com.example.rickmorty.app.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Location(
    @field:Json(name = "id")
    val id : Int? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "type")
    val type : String? = null,
    @field:Json(name = "dimension")
    val dimension : String? = null,
    @field:Json(name = "residents")
    val residents : List<String> = arrayListOf(),
    @field:Json(name = "url")
    val url: String? = null,
    @field:Json(name = "created")
    val created : String? = null
):Parcelable