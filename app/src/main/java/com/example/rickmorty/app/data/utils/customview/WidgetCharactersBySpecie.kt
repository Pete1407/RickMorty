package com.example.rickmorty.app.data.utils.customview

import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.databinding.AdapterCharactersPartBySpecieBinding
import java.util.jar.Attributes

class WidgetCharactersBySpecie(context, attrs : AttributeSet):LinearLayoutCompat(context, attrs) {

    private val binding : AdapterCharactersPartBySpecieBinding by lazy{
        AdapterCharactersPartBySpecieBinding.inflate(LayoutInflater.from(context),this,true)
    }

    fun setCharacterBySpecie(title : String, list : List<Character>){


    }
}