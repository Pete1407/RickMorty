package com.example.rickmorty.app.data.utils.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.R
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.databinding.ItemAdapterLocationBinding

class LocationAdapter(val list : ArrayList<Location>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var eventClickLocationListener : ((location : Location) -> Unit)? = null
    fun setEventClickLocationListener(event : ((location : Location) -> Unit)){
        eventClickLocationListener = event
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LocationViewHolder(ItemAdapterLocationBinding.inflate(LayoutInflater.from(parent.context),null,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if(holder is LocationViewHolder){
           holder.bind(list[position])
       }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun refreshList(newData : List<Location>){
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }

    fun addNewItems(newList : List<Location>){
        val lastPosition = list.size+1
        list.addAll(newList)
        notifyItemRangeInserted(lastPosition,newList.size)
    }

    inner class LocationViewHolder(val binding : ItemAdapterLocationBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(itemLocation : Location){
            val context = binding.root.context
            binding.nameLocation.text = itemLocation.name
            binding.typeLocation.text = "${context.getString(R.string.type_title)}: ${itemLocation.type}"
            binding.dimensionLocation.text = "${context.getString(R.string.dimension_title)}: ${itemLocation.dimension}"
            binding.root.setOnClickListener {
                eventClickLocationListener?.invoke(itemLocation)
            }

        }
    }

}