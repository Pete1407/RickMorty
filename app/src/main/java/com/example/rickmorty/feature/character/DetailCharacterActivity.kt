package com.example.rickmorty.feature.character

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseActivity
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.data.utils.ToastView
import com.example.rickmorty.databinding.ActivityInfoCharacterBinding
import com.example.rickmorty.feature.location.DetailLocationActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailCharacterActivity : BaseActivity(), CustomState {
    private val binding: ActivityInfoCharacterBinding by lazy {
        ActivityInfoCharacterBinding.inflate(layoutInflater)
    }
    private var figure: Character? = null

    @Inject
    lateinit var viewModelFactory: CharacterViewModelFactory

    private lateinit var viewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        figure = intent.getParcelableExtra<Character>(RMKey.ITEM_CHARACTER) as Character
        initListener()
        initViewModel()
        initUI()
    }

    override fun initUI() {
        viewModel.getCharacterBySpecific(figure?.id.toString())
    }

    override fun initListener() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.clickLocation.setOnClickListener {
            DetailLocationActivity.start(this,figure!!.location)
        }
        binding.clickEpsiode.setOnClickListener {

        }
    }


    override fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(CharacterViewModel::class.java)
        viewModel.specificData.observe(this,state)
    }

    override fun showLoading() {
        binding.loading.showLoading()
    }

    override fun hideLoading() {
        binding.loading.hideLoading()
    }

    private val state = Observer<Resource<Character>>{
        when(it){
            is Resource.Loading ->{
                showLoading()
            }
            is Resource.Success ->{
                hideLoading()
                it.data?.let {
                    updateUI(it)
                }

            }
            else ->{
                hideLoading()
                ToastView(this).showShortToast(it.message.toString())
            }
        }
    }

    private fun updateUI(character: Character){
        binding.apply {
            Glide.with(this@DetailCharacterActivity)
                .load(character.image)
                .into(this.imageCharacter)

            this.nameCharacter.text = character.name
            if(character.species != ""){
                this.specieText.text = "${getString(R.string.specie_title)}: ${character.species.toString().lowercase()}"
            }else{
                this.genderText.text = "${getString(R.string.gender_title)}: ${getString(R.string.no_data)}"
            }
            if(character.gender != ""){
                this.genderText.text = "${getString(R.string.gender_title)}: ${character.gender.toString().lowercase()}"
            }else{
                this.genderText.text = "${getString(R.string.gender_title)}: ${getString(R.string.no_data)}"
            }
            if(character.type != ""){
                this.typeText.text = "${getString(R.string.type_title)}: ${character.type.toString().lowercase()}"
            }else{
                this.typeText.text = "${getString(R.string.type_title)}: ${getString(R.string.no_data)}"
            }
        }
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