package com.example.rickmorty.base.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.rickmorty.R

class CustomToolBar @JvmOverloads constructor(
    context : Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context,attributeSet) {

    private var textTitle : String? = null

    init{
        attributeSet?.let{ setAttribure(it)}
    }

    private fun setAttribure(attributeSet: AttributeSet){
        val arr = context.obtainStyledAttributes(attributeSet, R.styleable.CustomToolBar)

    }
}