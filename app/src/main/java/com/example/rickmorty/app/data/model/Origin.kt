package com.example.rickmorty.app.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Origin(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url")
    val url: String
):Parcelable