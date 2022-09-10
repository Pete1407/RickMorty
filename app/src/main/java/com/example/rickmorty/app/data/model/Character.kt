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
    val created: String? = null,
    @field:Json(name = "episode")
    val episode: List<String> = arrayListOf(),
    @field:Json(name = "gender")
    val gender: String? = null,
    @field:Json(name = "id")
    val id: Int = 0,
    @field:Json(name = "image")
    val image: String? = null,
    @field:Json(name = "location")
    val location: Location,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "origin")
    val origin: Origin? = null,
    @field:Json(name = "species")
    val species: String? = null,
    @field:Json(name = "status")
    val status: String? = null,
    @field:Json(name = "type")
    val type: String? = null,
    @field:Json(name = "url")
    val url: String? = null
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