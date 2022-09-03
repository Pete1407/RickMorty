package com.example.rickmorty.app.data.utils.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.databinding.ItemCardCharacterBinding
import android.widget.LinearLayout
import com.example.rickmorty.R
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.utils.customview.WidgetCharactersBySpecie
import com.example.rickmorty.databinding.AdapterCharactersPartBySpecieBinding
import com.example.rickmorty.databinding.AdapterCharactersPartBySpecieBinding.bind
import com.example.rickmorty.databinding.AdapterCharactersPartBySpecieBinding.inflate
import com.example.rickmorty.databinding.WidgetTitleAdapterHomeBinding
import okhttp3.internal.addHeaderLenient


class CharactersAdapter(
    val context: Context,
    val humanList: ArrayList<Character>,
    val alienList : ArrayList<Character>,
    val animalList : ArrayList<Character>,
    val unknownList : ArrayList<Character>,
    var allList : ArrayList<Character>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HUMAN = 0
    private val TYPE_ALIEN = 1
    private val TYPE_ANIMAL = 2
    private val TYPE_UNKNOWN = 3
    private val TYPE_ALL = 4
    private val TYPE_ITEM = 5

    private val positionOther = 5


    override fun getItemViewType(position: Int): Int {
        return when(position){
            0 ->{
                TYPE_HUMAN
            }
            1 ->{
                TYPE_ALIEN
            }
            2 ->{
                TYPE_ANIMAL
            }
            3 ->{
                TYPE_UNKNOWN
            }
            4 ->{
                TYPE_ALL
            }
            else -> {
                TYPE_ITEM
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if(viewType == TYPE_HUMAN){
            return CategoryCharacterBySpecie(AdapterCharactersPartBySpecieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
        else if(viewType == TYPE_ALIEN){
            return CategoryCharacterBySpecie(AdapterCharactersPartBySpecieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
        else if(viewType == TYPE_ANIMAL){
            return CategoryCharacterBySpecie(AdapterCharactersPartBySpecieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
        else if(viewType == TYPE_UNKNOWN){
            return CategoryCharacterBySpecie(AdapterCharactersPartBySpecieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
        else if(viewType == TYPE_ALL){
            return AllTitleViewHolder(WidgetTitleAdapterHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        }
        else{
            return SpecificedCharacter(ItemCardCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            TYPE_HUMAN -> {
                if(holder is CategoryCharacterBySpecie){
                    holder.setPartOfSpecie(context.getString(R.string.category_human),humanList)
                    //Log.d(RMKey.DEBUG_TAG,"HUMAN :: $position")
                }
            }
            TYPE_ANIMAL -> {
                if(holder is CategoryCharacterBySpecie){
                    holder.setPartOfSpecie(context.getString(R.string.category_animal),animalList)
                    //Log.d(RMKey.DEBUG_TAG,"ANIMAL :: $position")
                }
            }
            TYPE_ALIEN -> {
                if(holder is CategoryCharacterBySpecie){
                    holder.setPartOfSpecie(context.getString(R.string.category_alien),alienList)
                    //Log.d(RMKey.DEBUG_TAG,"ALIEN :: $position")
                }
            }
            TYPE_UNKNOWN -> {
                if (holder is CategoryCharacterBySpecie) {
                    holder.setPartOfSpecie(context.getString(R.string.category_unknown), unknownList)
                    //Log.d(RMKey.DEBUG_TAG,"UNKNOWN :: $position")
                }
            }
            TYPE_ALL -> {
                if(holder is AllTitleViewHolder){
                    holder.setTitle(context.getString(R.string.category_all))
                    //Log.d(RMKey.DEBUG_TAG,"ALL :: $position")
                }
            }
            TYPE_ITEM -> {
                //Log.d(RMKey.DEBUG_TAG,"ITEM :: $position")
                //Log.d(RMKey.DEBUG_TAG,"All List --> ${allList.size}")
                //Log.d(RMKey.DEBUG_TAG,"$position   $positionOther  --> ${position-positionOther}")
                if(holder is SpecificedCharacter){
                    holder.setCharacter(allList[position-positionOther])
                }
            }

        }

    }

    override fun getItemCount(): Int {
        return positionOther+allList.size
    }

    fun refreshHumanList(list : List<Character>){
        humanList.clear()
        humanList.addAll(list)
        notifyItemChanged(TYPE_HUMAN)
    }

    fun refreshAlienList(list : List<Character>){
        alienList.clear()
        alienList.addAll(list)
        notifyItemChanged(TYPE_ALIEN)
    }

    fun refreshAnimalList(list : List<Character>){
        animalList.clear()
        animalList.addAll(list)
        notifyItemChanged(TYPE_ANIMAL)
    }

    fun refreshUnknownList(list : List<Character>){
        unknownList.clear()
        unknownList.addAll(list)
        notifyItemChanged(TYPE_UNKNOWN)
    }

    fun refreshAllList(list : List<Character>){
        allList.clear()
        allList = list as ArrayList<Character>
        notifyDataSetChanged()
    }

    fun addNewItems(newList : List<Character>){
        val lastPosition = allList.size
        allList.addAll(newList)
        notifyItemRangeInserted()
    }

    inner class CategoryCharacterBySpecie(val binding : AdapterCharactersPartBySpecieBinding):RecyclerView.ViewHolder(binding.root){

        private var adapter : CharacterAdapter? = null

        fun setPartOfSpecie(titleText : String,itemList : List<Character>){
            val context = binding.root.context
            binding.title.setTextTitle(titleText)
            if(adapter == null){
                adapter = CharacterAdapter(itemList)
            }

            binding.recyclerView.adapter = adapter
        }
    }

    inner class SpecificedCharacter(val binding : ItemCardCharacterBinding):RecyclerView.ViewHolder(binding.root){

        fun setCharacter(character : Character){
            binding.nameCharacter.text = character.name
            Glide.with(binding.root.context)
                .load(character.image)
                .into(binding.imageCharacter)
        }
    }

    inner class AllTitleViewHolder(val binding : WidgetTitleAdapterHomeBinding):RecyclerView.ViewHolder(binding.root){

        fun setTitle(text : String){
            binding.titleText.text = text
        }

    }

}