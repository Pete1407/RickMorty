package com.example.rickmorty.app.data.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmorty.R
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.databinding.ItemCardCharacterBinding

class CharacterAdapter(val list : ArrayList<Character>) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    var chooseListener : ((item : Character)-> Unit)? = null

   fun setChooseEvent(event : (item:Character)->Unit){
       chooseListener = event
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterAdapter.CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_card_character, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
            holder.setItem(list[position],chooseListener!!)

    }

    override fun getItemCount(): Int {
       return list.size
    }


    inner class CharacterViewHolder(val view : View):RecyclerView.ViewHolder(view){

        private val binding = ItemCardCharacterBinding.bind(view)

        fun setItem(item : Character,event : (item:Character)->Unit){
            Glide.with(binding.imageCharacter)
                .load(item.image)
                .into(binding.imageCharacter)
            binding.nameCharacter.text = item.name
            binding.root.setOnClickListener {
                event.invoke(item)
            }

        }
    }
}