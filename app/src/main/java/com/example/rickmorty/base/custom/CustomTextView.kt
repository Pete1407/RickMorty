package com.example.rickmorty.base.custom

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.res.ResourcesCompat
import com.example.rickmorty.R

class CustomTextView @JvmOverloads constructor(
    context : Context,
    attributeSet: AttributeSet? = null
): AppCompatTextView(context,attributeSet) {

    private var titleText : String = ""
    private var titleSize : Int = 0
    private var titleColor : Int = 0
    private var styleTitle : Int = 0
    init{
        setAttributes(attributeSet)
    }

    private fun setAttributes(attributeSet: AttributeSet?){
        val attr = context.obtainStyledAttributes(attributeSet, R.styleable.CustomTextView)
        titleText = attr.getString(R.styleable.CustomTextView_titleText).toString()
        titleSize = attr.getDimensionPixelSize(R.styleable.CustomTextView_titleSize,0)
        titleColor = attr.getColor(R.styleable.CustomTextView_titleColor,0)
        styleTitle = attr.getInt(R.styleable.CustomTextView_styleText,0)
    }


}