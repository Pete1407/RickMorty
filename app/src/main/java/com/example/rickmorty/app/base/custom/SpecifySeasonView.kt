package com.example.rickmorty.app.base.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.rickmorty.app.data.model.Episode
import com.example.rickmorty.app.data.utils.adapter.EpisodeAdapter
import com.example.rickmorty.app.data.utils.adapter.EpisodesAdapter
import com.example.rickmorty.databinding.AdapterSpecifySeasonBinding

class SpecifySeasonView(context : Context, attrs : AttributeSet? = null):LinearLayoutCompat(context, attrs) {

    private var adapter : EpisodeAdapter? = null

    private val binding : AdapterSpecifySeasonBinding by lazy {
        AdapterSpecifySeasonBinding.inflate(LayoutInflater.from(context),this,true)
    }

    fun setDataEachSeason(seasonText : String,list : ArrayList<Episode>){
        binding.titleSeason.text = seasonText
        adapter = EpisodeAdapter(list)
        binding.seasonRecyclerView.adapter = adapter
    }


}