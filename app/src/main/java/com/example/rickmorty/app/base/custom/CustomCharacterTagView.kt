package com.example.rickmorty.app.base.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.bumptech.glide.Glide
import com.example.rickmorty.R
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Episode
import com.example.rickmorty.databinding.WidgetCharacterItemBinding
import de.hdodenhof.circleimageview.CircleImageView

class CustomCharacterTagView(context: Context,attrs : AttributeSet?):LinearLayoutCompat(context,attrs) {

    private val binding : WidgetCharacterItemBinding by lazy {
        WidgetCharacterItemBinding.inflate(LayoutInflater.from(context),this,true)
    }

    fun setCharacterBind(list : List<Character>){
        // condition 1 :: for character more than five figures, will show more number.
        if(list.size >= 5){
            createCharacterImage(list)
        }
        // condition 2 :: for character less than five figures but not zero
        else if(list.size < 5 && list.isNotEmpty()){
            createCharacterImage(list)
        }
        // condition3 :: for no character
        else{

        }
    }

    private fun createCharacterImage(list : List<Character>){
        var circleImgList = ArrayList<CircleImageView>()



    }
}