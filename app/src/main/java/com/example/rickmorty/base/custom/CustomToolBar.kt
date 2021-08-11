package com.example.rickmorty.base.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.rickmorty.R

class CustomToolBar @JvmOverloads constructor(
    context : Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context,attributeSet) {

    private var binding = LayoutInflater.from(context).inflate(R.layout.custom_toolbar,this,true)

    private var textTitle : String? = null

    init{
        setAttributes(attributeSet)
    }

    private fun setAttributes(attributeSet: AttributeSet?){

    }

}