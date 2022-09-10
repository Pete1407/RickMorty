package com.example.rickmorty.app.data.utils.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.rickmorty.databinding.WidgetCharacterItemBinding

class WidgetCharacterTag(context : Context, attrs : AttributeSet): LinearLayoutCompat(context,attrs) {

    private val binding : WidgetCharacterItemBinding by lazy {
        WidgetCharacterItemBinding.inflate(LayoutInflater.from(context),this,true)
    }


}