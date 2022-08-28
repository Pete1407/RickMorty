package com.example.rickmorty.app.data.utils.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmorty.R
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.databinding.ItemCardCharacterBinding
import android.app.Activity
import android.widget.LinearLayout


class CharacterAdapter(val list: ArrayList<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val TYPE_HUMAN = 0
    private val TYPE_ALIEN = 1
    private val TYPE_ANIMAL = 2
    private val TYPE_UNKNOWN = 3
    private val TYPE_ALL = 4

    var chooseListener: ((item: Character) -> Unit)? = null

    fun setChooseEvent(event: (item: Character) -> Unit) {
        chooseListener = event
    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            TYPE_HUMAN ->{
                TYPE_HUMAN
            }
            TYPE_ALIEN ->{
                TYPE_ALIEN
            }
            TYPE_ANIMAL ->{
                TYPE_ANIMAL
            }
            TYPE_UNKNOWN ->{
                TYPE_UNKNOWN
            }
            else -> {
                TYPE_ALL
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.CharacterViewHolder {
        val viewHolder =
            ItemCardCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val width = (parent.context.resources.displayMetrics.widthPixels * 0.5).toInt()
        viewHolder.mainLayout.layoutParams =
            LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT)
        return CharacterViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharacterViewHolder, position: Int) {
        holder.setItem(list[position], chooseListener!!)

    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class CharacterViewHolder(private val binding: ItemCardCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun setItem(item: Character, event: (item: Character) -> Unit) {
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