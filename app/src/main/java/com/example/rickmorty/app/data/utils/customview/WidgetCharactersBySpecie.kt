package com.example.rickmorty.app.data.utils.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.utils.adapter.CharacterAdapter
import com.example.rickmorty.databinding.AdapterCharactersPartBySpecieBinding
import java.util.jar.Attributes

// TODO: ลบด้วยไม่ได้ใช้แล้ว
class WidgetCharactersBySpecie(context : Context, attrs : AttributeSet):LinearLayoutCompat(context, attrs) {

    private var adapter : CharacterAdapter? = null

    private val binding : AdapterCharactersPartBySpecieBinding by lazy{
        AdapterCharactersPartBySpecieBinding.inflate(LayoutInflater.from(context),this,true)
    }

    fun setCharacterBySpecie(titleText : String, list : List<Character>){
        binding.title.setTextTitle(titleText)
        if(adapter == null){
            adapter = CharacterAdapter(list)
        }
        binding.recyclerView.adapter = adapter


    }
}