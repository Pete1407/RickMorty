package com.example.rickmorty.app.data.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.databinding.ItemCardCharacterBinding


class CharacterAdapter(val list : List<Character>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var eventClickDetailCharacter : ((data : Character)->Unit)? = null
    fun setEventClickDetailListener(event : ((data : Character)->Unit)){
        this.eventClickDetailCharacter = event
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(ItemCardCharacterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            holder.setCharacter(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding : ItemCardCharacterBinding):RecyclerView.ViewHolder(binding.root){

        fun setCharacter(character : Character){
            binding.nameCharacter.text = character.name
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(CenterCrop(), RoundedCorners(16))
            Glide.with(binding.root.context)
                .load(character.image)
                .apply(requestOptions)
                .into(binding.imageCharacter)
            binding.root.setOnClickListener {
                eventClickDetailCharacter?.invoke(character)
            }
        }
    }


}