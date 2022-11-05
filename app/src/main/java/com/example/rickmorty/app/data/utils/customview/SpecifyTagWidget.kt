package com.example.rickmorty.app.data.utils.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.rickmorty.R
import com.example.rickmorty.databinding.WidgetSpecifyTagBinding

class SpecifyTagWidget(context : Context, val attrs : AttributeSet? = null): LinearLayoutCompat(context,attrs) {

    private var title : String = ""

    private val binding : WidgetSpecifyTagBinding by lazy {
        WidgetSpecifyTagBinding.inflate(LayoutInflater.from(context),this,true)
    }

    init {
        setAttribute()
    }

    private fun setAttribute(){
        val attr = context.obtainStyledAttributes(attrs, R.styleable.SpecifyTagWidget)
        val textValue = attr.getString(R.styleable.SpecifyTagWidget_titleTag)?:""
        title = textValue
        //setSeasonOrEpisodeTitle(title)
    }

//    private fun setSeasonOrEpisodeTitle(title : String){
//        binding.textTag.text = title
//    }


    fun setValueTag(text: String) {
        binding.textTag.text = text
    }

}