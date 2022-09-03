package com.example.rickmorty.app.base.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.rickmorty.app.data.utils.extension.gone
import com.example.rickmorty.app.data.utils.extension.visible
import com.example.rickmorty.databinding.WidgetCustomLoadingBinding

class CustomLoading(context : Context,attrs : AttributeSet):LinearLayoutCompat(context,attrs) {

    private val binding : WidgetCustomLoadingBinding by lazy {
        WidgetCustomLoadingBinding.inflate(LayoutInflater.from(context),this,true)
    }

    fun showLoading(){
        binding.root.visible()
        binding.loading.visible()
        binding.loading.playAnimation()
    }

    fun hideLoading(){
        binding.root.gone()
        binding.loading.gone()
        binding.loading.pauseAnimation()
    }
}