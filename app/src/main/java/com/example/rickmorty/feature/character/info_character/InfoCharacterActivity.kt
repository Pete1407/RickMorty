package com.example.rickmorty.feature.character.info_character

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseActivity
import com.example.rickmorty.app.base.RmKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.databinding.ActivityInfoCharacterBinding

class InfoCharacterActivity : BaseActivity() {
    private lateinit var binding : ActivityInfoCharacterBinding
    private var characterChosen : Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoCharacterBinding.inflate(layoutInflater)
        characterChosen = intent.getParcelableExtra<Character>(RmKey.ITEM_CHARACTER) as Character
        val view = binding.root
        setContentView(view)
        initUI()
    }

    private fun initUI(){
        characterChosen?.let{
            binding.toolBar.setTextTitle(it.name)
        }?:kotlin.run {
            binding.toolBar.setTextTitle(resources.getString(R.string.info_character))
        }

        binding.toolBar.setOnClickListener {
            finish()
        }

    }

    companion object{

        fun create(context : Context,item : Character):Intent{
            val intent = Intent(context,InfoCharacterActivity::class.java)
            intent.putExtra(RmKey.ITEM_CHARACTER,item)
            return intent
        }
    }
}