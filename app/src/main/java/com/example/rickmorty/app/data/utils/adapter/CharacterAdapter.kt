package com.example.rickmorty.app.data.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.databinding.ItemCardCharacterBinding

class CharacterAdapter(val list : List<Character>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            Glide.with(binding.root.context)
                .load(character.image)
                .into(binding.imageCharacter)
        }
    }


}