package com.example.rickmorty.app.data.model

import android.content.Context
import android.os.Parcelable
import android.util.Log
import com.example.rickmorty.R
import com.example.rickmorty.app.base.RmKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
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
):Parcelable
{
    fun getBackgroundStatus():Int{
        return when(status){
            RmKey.TYPE_ALIVE -> R.drawable.bg_status_alive
            RmKey.TYPE_DEAD -> R.drawable.bg_status_dead
            else -> R.drawable.bg_status_unknown
        }
    }

    fun getStatus(context : Context):String{
        Log.i("result","result --> $status")
        return when(status){
            RmKey.TYPE_ALIVE -> context.resources.getString(R.string.alive)
            RmKey.TYPE_DEAD -> context.resources.getString(R.string.dead)
            else -> context.resources.getString(R.string.unknown)
        }
    }

    fun getIconStatus():Int{
        return when(status){
            RmKey.TYPE_ALIVE -> R.drawable.icon_live
            RmKey.TYPE_DEAD -> R.drawable.icon_dead
            else -> R.drawable.icon_not_avaliable
        }
    }
}