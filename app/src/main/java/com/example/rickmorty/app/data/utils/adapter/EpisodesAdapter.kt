package com.example.rickmorty.app.data.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.app.data.model.Episode
import com.example.rickmorty.app.data.utils.extension.gone
import com.example.rickmorty.databinding.ItemAdapterEpisodeBinding

class EpisodesAdapter(val list : List<Episode>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EpisodeViewHolder(ItemAdapterEpisodeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is EpisodeViewHolder){
            holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class EpisodeViewHolder(val binding : ItemAdapterEpisodeBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(episode : Episode){
            binding.nameLocation.text = episode.name
            binding.characterTag.gone()
            //binding.typeLocation.text = episode.
        }
    }

}