package com.example.rickmorty.base.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.rickmorty.R
import com.example.rickmorty.databinding.WidgetCustomToolbarBinding

class CustomToolBar @JvmOverloads constructor(
    context : Context,
    attributeSet: AttributeSet? = null
): LinearLayout(context,attributeSet){

    private var binding = WidgetCustomToolbarBinding.inflate(LayoutInflater.from(context),this,true)
    private var textTitle : String = ""
    private var isShowButton : Boolean = false

    init {
        setAttributes(attributeSet)
    }

    private fun setAttributes(attributeSet: AttributeSet?){
        val attr = context.obtainStyledAttributes(attributeSet,R.styleable.CustomToolBar)
        textTitle = attr.getString(R.styleable.CustomToolBar_addText).toString()
        isShowButton = attr.getBoolean(R.styleable.CustomToolBar_showBackButton,false)
        setTextTitle(textTitle)
        setShowButton(isShowButton)
    }

    private fun setTextTitle(txt : String){
        binding.title.text = txt
    }

    private fun setShowButton(isShow : Boolean){
        if(isShow){
            binding.backButton.visibility = View.VISIBLE
        }else{
            binding.backButton.visibility = View.GONE
        }

    }

}


