package com.example.rickmorty.app.data.utils.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.databinding.CharacterTagCustomViewBinding
import de.hdodenhof.circleimageview.CircleImageView

class Character_custom_view_tag(context : Context,attrs : AttributeSet): LinearLayoutCompat(context,attrs) {

    private val binding : CharacterTagCustomViewBinding by lazy {
        CharacterTagCustomViewBinding.inflate(LayoutInflater.from(context), this, true)
    }
        fun setCharacters(list : List<Character>){
        }

}