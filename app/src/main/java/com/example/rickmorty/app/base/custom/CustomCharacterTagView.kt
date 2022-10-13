package com.example.rickmorty.app.base.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.rickmorty.app.data.model.Episode
import com.example.rickmorty.databinding.WidgetCharacterItemBinding

class CustomCharacterTagView(context: Context,attrs : AttributeSet?):LinearLayoutCompat(context,attrs) {

    private val binding : WidgetCharacterItemBinding by lazy {
        WidgetCharacterItemBinding.inflate(LayoutInflater.from(context),this,true)
    }

    fun setCharacterBind(epList : List<Episode>){

    }
}