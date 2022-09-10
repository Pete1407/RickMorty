package com.example.rickmorty.app.data.utils.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R
import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.databinding.AdapterLocationBinding

class LocationAdapter(val list : List<Location>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LocationViewHolder(AdapterLocationBinding.inflate(LayoutInflater.from(parent.context),null,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if(holder is LocationViewHolder){
           holder.bind(list[position])
       }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class LocationViewHolder(val binding : AdapterLocationBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(itemLocation : Location){
            val context = binding.root.context
            binding.nameLocation.text = itemLocation.name
            binding.typeLocation.text = "${context.getString(R.string.type_title)}: ${itemLocation.type}"
            binding.dimensionLocation.text = "${context.getString(R.string.dimension_title)}: ${itemLocation.dimension}"

        }
    }

}