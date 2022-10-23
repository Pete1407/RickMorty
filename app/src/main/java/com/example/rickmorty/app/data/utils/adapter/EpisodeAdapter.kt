package com.example.rickmorty.app.data.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Episode
import com.example.rickmorty.databinding.ItemCardCharacterBinding
import com.example.rickmorty.databinding.ItemCardEpisodeBinding


class EpisodeAdapter(val list : List<Episode>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var eventClickDetailCharacter : ((data : Episode)->Unit)? = null
    fun setEventClickDetailListener(event : ((data : Episode)->Unit)){
        this.eventClickDetailCharacter = event
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemCardEpisodeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            holder.setEpisode(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding : ItemCardEpisodeBinding):RecyclerView.ViewHolder(binding.root){

        fun setEpisode(aEpisode : Episode){
            binding.episodeName.text = aEpisode.name
            binding.episodeNumber.text = aEpisode.episodeText
            binding.episodeOnAir.text = aEpisode.air_date
            binding.root.setOnClickListener {
                eventClickDetailCharacter?.invoke(aEpisode)
            }
        }
    }


}