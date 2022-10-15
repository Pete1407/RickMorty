package com.example.rickmorty.app.base.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.rickmorty.app.data.model.Episode
import com.example.rickmorty.app.data.utils.adapter.EpisodesAdapter
import com.example.rickmorty.databinding.AdapterSpecifySeasonBinding

class SpecifySeasonView(context : Context, attrs : AttributeSet? = null):LinearLayoutCompat(context, attrs) {

    //private var adapter : EpisodesAdapter? = null

    private val binding : AdapterSpecifySeasonBinding by lazy {
        AdapterSpecifySeasonBinding.inflate(LayoutInflater.from(context),this,true)
    }

    fun setDataEachSeason(list : ArrayList<Episode>){

    }


}