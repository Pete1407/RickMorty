package com.example.rickmorty.app.base.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.rickmorty.R
import com.example.rickmorty.app.data.utils.extension.gone
import com.example.rickmorty.app.data.utils.extension.visible
import com.example.rickmorty.databinding.WidgetCustomToolbarBinding

class CustomToolBar @JvmOverloads constructor(
    context : Context,
    attributeSet: AttributeSet? = null
): LinearLayout(context,attributeSet){

    private var binding = WidgetCustomToolbarBinding.inflate(LayoutInflater.from(context),this,true)
    private var textTitle : String = ""
    private var isShowButton : Boolean = false
    private var isShowTitle : Boolean = false

    private var onclickListener : (()-> Unit)? = null


    init {
        setAttributes(attributeSet)
    }

    fun setOnClickListener(event : ()-> Unit){
        onclickListener = event
    }

    private fun setAttributes(attributeSet: AttributeSet?){
        val attr = context.obtainStyledAttributes(attributeSet,R.styleable.CustomToolBar)
        textTitle = attr.getString(R.styleable.CustomToolBar_textTitle).toString()
        isShowButton = attr.getBoolean(R.styleable.CustomToolBar_showBackButton,false)
        isShowTitle = attr.getBoolean(R.styleable.CustomToolBar_showTitle,false)

        setTextTitle(textTitle)
        setShowButton(isShowButton)
        showTitle(isShowTitle)

        binding.backButton.setOnClickListener {
            onclickListener?.invoke()
        }

    }

    fun setTextTitle(txt : String){
        binding.title.text = txt
    }

    private fun setShowButton(isShow : Boolean){
        if(isShow){
            binding.backButton.visible()
        }else{
            binding.backButton.gone()
        }

    }

    private fun showTitle(isShow: Boolean){
        if(isShow){
            binding.title.visible()
        }else{
            binding.title.gone()
        }
    }

}


