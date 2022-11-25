package com.example.rickmorty.app.data.utils.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.rickmorty.R
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.databinding.AdapterCharactersPartBySpecieBinding
import com.example.rickmorty.databinding.ItemCardCharacterBinding
import com.example.rickmorty.databinding.WidgetTitleAdapterHomeBinding


class CharactersAdapter(
    val context: Context,
    var humanList: ArrayList<Character>,
    var alienList : ArrayList<Character>,
    var animalList : ArrayList<Character>,
    var unknownList : ArrayList<Character>,
    var allList : ArrayList<Character>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HUMAN = 0
    private val TYPE_ALIEN = 1
    private val TYPE_ANIMAL = 2
    private val TYPE_UNKNOWN = 3
    private val TYPE_ALL = 4
    private val TYPE_ITEM = 5

    private val positionOther = 5

    private var eventClickDetailCharacter : ((data : Character)->Unit)? = null
    fun setEventClickDetailListener(event : ((data : Character)->Unit)){
        this.eventClickDetailCharacter = event
    }

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
                }
            }
            TYPE_ANIMAL -> {
                if(holder is CategoryCharacterBySpecie){
                    holder.setPartOfSpecie(context.getString(R.string.category_animal),animalList)
                }
            }
            TYPE_ALIEN -> {
                if(holder is CategoryCharacterBySpecie){
                    holder.setPartOfSpecie(context.getString(R.string.category_alien),alienList)
                }
            }
            TYPE_UNKNOWN -> {
                if (holder is CategoryCharacterBySpecie) {
                    holder.setPartOfSpecie(context.getString(R.string.category_unknown), unknownList)
                }
            }
            TYPE_ALL -> {
                if(holder is AllTitleViewHolder){
                    holder.setTitle(context.getString(R.string.category_all))
                }
            }
            TYPE_ITEM -> {
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
        allList = ArrayList(list)
        notifyDataSetChanged()
    }

    fun addNewItems(newList : List<Character>){
        val lastPosition = allList.size+1
        allList.addAll(newList)
        notifyItemRangeInserted(lastPosition,newList.size)
    }

    inner class CategoryCharacterBySpecie(val binding : AdapterCharactersPartBySpecieBinding):RecyclerView.ViewHolder(binding.root){

        private var adapter : CharacterAdapter? = null

        fun setPartOfSpecie(titleText: String, itemList: List<Character>) {
            val context = binding.root.context
            binding.title.setTextTitle(titleText)
            adapter = CharacterAdapter(ArrayList(itemList))
            adapter!!.setEventClickDetailListener {
                eventClickDetailCharacter?.invoke(it)
            }

            binding.recyclerView.adapter = adapter
        }
    }

    inner class SpecificedCharacter(val binding : ItemCardCharacterBinding):RecyclerView.ViewHolder(binding.root){

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

    inner class AllTitleViewHolder(val binding : WidgetTitleAdapterHomeBinding):RecyclerView.ViewHolder(binding.root){

        fun setTitle(text : String){
            binding.titleText.text = text
        }

    }

}