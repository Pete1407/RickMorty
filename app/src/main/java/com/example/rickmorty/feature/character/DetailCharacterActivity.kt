package com.example.rickmorty.feature.character

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseActivity
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.databinding.ActivityInfoCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailCharacterActivity : BaseActivity(), CustomState {
    private lateinit var binding: ActivityInfoCharacterBinding
    private var figure: Character? = null

    @Inject
    lateinit var viewModelFactory: CharacterViewModelFactory

    private lateinit var viewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoCharacterBinding.inflate(layoutInflater)
        figure = intent.getParcelableExtra<Character>(RMKey.ITEM_CHARACTER) as Character
        setContentView(binding.root)
        initListener()
        initViewModel()
        initUI()
    }

    override fun initUI() {
        binding.apply {
            Glide.with(this@DetailCharacterActivity)
            .load(figure?.image)
            .into(this.imageCharacter)

            this.nameCharacter.text = figure?.name
            if(figure?.species != ""){
                this.specieText.text = "${getString(R.string.specie_title)}: ${figure?.species.toString().lowercase()}"
            }else{
                this.genderText.text = "${getString(R.string.gender_title)}: ${getString(R.string.no_data)}"
            }
            if(figure?.gender != ""){
                this.genderText.text = "${getString(R.string.gender_title)}: ${figure?.gender.toString().lowercase()}"
            }else{
                this.genderText.text = "${getString(R.string.gender_title)}: ${getString(R.string.no_data)}"
            }
            if(figure?.type != ""){
                this.typeText.text = "${getString(R.string.type_title)}: ${figure?.type.toString().lowercase()}"
            }else{
                this.typeText.text = "${getString(R.string.type_title)}: ${getString(R.string.no_data)}"
            }
        }
    }

    override fun initListener() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.clickLocation.setOnClickListener {

        }
        binding.clickEpsiode.setOnClickListener {

        }
    }


    override fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(CharacterViewModel::class.java)
    }

    override fun showLoading() {
        //binding.loading.playAnimation()
        //binding.loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        //binding.loading.visibility = View.GONE
    }

    companion object {
        private const val ITEM_CHARACTER = "item-character"

        @JvmStatic
        fun create(context: Context, item: Character) {
            context.startActivity(Intent(context, DetailCharacterActivity::class.java).apply {
                putExtra(ITEM_CHARACTER, item)
            })
        }
    }
}