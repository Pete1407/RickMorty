package com.example.rickmorty.app.data.model

import android.content.Context
import android.os.Parcelable
import android.util.Log
import com.example.rickmorty.R
import com.example.rickmorty.app.base.RMKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Character(
    @field:Json(name = "created")
    val created: String,
    @field:Json(name = "episode")
    val episode: List<String>,
    @field:Json(name = "gender")
    val gender: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "image")
    val image: String,
    @field:Json(name = "location")
    val location: Location,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "origin")
    val origin: Origin,
    @field:Json(name = "species")
    val species: String,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "type")
    val type: String,
    @field:Json(name = "url")
    val url: String
):Parcelable
{
    fun getBackgroundStatus():Int{
        return when(status){
            RMKey.TYPE_ALIVE -> R.drawable.bg_status_alive
            RMKey.TYPE_DEAD -> R.drawable.bg_status_dead
            else -> R.drawable.bg_status_unknown
        }
    }

    fun getStatus(context : Context):String{
        Log.i("result","result --> $status")
        return when(status){
            RMKey.TYPE_ALIVE -> context.resources.getString(R.string.alive)
            RMKey.TYPE_DEAD -> context.resources.getString(R.string.dead)
            else -> context.resources.getString(R.string.unknown)
        }
    }

    fun getIconStatus():Int{
        return when(status){
            RMKey.TYPE_ALIVE -> R.drawable.icon_live
            RMKey.TYPE_DEAD -> R.drawable.icon_dead
            else -> R.drawable.icon_not_avaliable
        }
    }
}