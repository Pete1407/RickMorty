package com.example.rickmorty.app.data.utils.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.rickmorty.databinding.WidgetCustomSearchbarBinding

class SearchBarWidget (
    context : Context,
    attrs : AttributeSet? = null
) : LinearLayoutCompat(context, attrs), OnEditorActionListener {

    private lateinit var binding : WidgetCustomSearchbarBinding

    init {
        initUI()
    }

    private fun initUI(){
        binding = WidgetCustomSearchbarBinding.inflate(LayoutInflater.from(context),this,true)
        binding.search.setOnEditorActionListener(this)
        binding.cancel.setOnClickListener {
            binding.search.setText("")
        }
    }

    fun getText():String{
        return binding.search.text.toString()
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if(actionId == EditorInfo.IME_ACTION_SEARCH){
            Log.d("search","search text --> ${v!!.text.toString()}")
            return true
        }
        return false
    }


}