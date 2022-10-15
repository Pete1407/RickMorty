package com.example.rickmorty.app.data.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.app.data.model.SeasonModel
import com.example.rickmorty.databinding.ItemAdapterEpisodeBinding

class EpisodesAdapter(val list : MutableList<SeasonModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

//    fun refreshList(newData : ArrayList<Episode>){
//        list.clear()
//        list.addAll(newData)
//        notifyDataSetChanged()
//    }

    inner class EpisodeViewHolder(val binding : ItemAdapterEpisodeBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(item : SeasonModel){
            //binding.seasonView.setDataEachSeason(item.episodeList)
        }
    }

}