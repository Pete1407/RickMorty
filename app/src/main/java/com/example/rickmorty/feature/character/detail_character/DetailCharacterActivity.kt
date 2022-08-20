package com.example.rickmorty.feature.character.detail_character

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseActivity
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.databinding.ActivityInfoCharacterBinding
import com.example.rickmorty.feature.character.CharacterViewModel
import com.example.rickmorty.feature.character.CharacterViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailCharacterActivity : BaseActivity(), CustomState {
    private lateinit var binding: ActivityInfoCharacterBinding
    private var characterChosen: Character? = null

    @Inject
    lateinit var viewModelFactory: CharacterViewModelFactory

    private lateinit var viewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoCharacterBinding.inflate(layoutInflater)
        characterChosen = intent.getParcelableExtra<Character>(RMKey.ITEM_CHARACTER) as Character
        val view = binding.root
        setContentView(view)
        binding.mainLayout.visibility = View.GONE
        //initUI()
        //initViewModel()
        //viewModel.getSingleCharacter(characterChosen?.id.toString())
    }

    override fun initUI() {
        characterChosen?.let {
            binding.toolBar.setTextTitle(it.name)
        } ?: kotlin.run {
            binding.toolBar.setTextTitle(resources.getString(R.string.info_character))
        }
        binding.toolBar.setOnClickListener {
            finish()
        }
    }

    private fun updateUI(data: Character) {
        binding.mainLayout.visibility = View.VISIBLE
        Glide.with(this)
            .load(data.image)
            .into(binding.imageProfile)
        binding.textName.text = data.name

        // set specie
        if (data.species.equals(RMKey.TYPE_HUMAN, ignoreCase = true)) {
            binding.textSpecie.text = RMKey.TYPE_HUMAN
            binding.iconSpecie.setImageResource(R.drawable.icon_person)
        } else {
            binding.textSpecie.text = RMKey.TYPE_ALIEN
            binding.iconSpecie.setImageResource(R.drawable.icon_alien)
        }

        // set gender
        if (data.gender.equals(RMKey.TYPE_MALE, ignoreCase = true)) {
            binding.textGender.text = RMKey.TYPE_MALE
            binding.iconGender.setImageResource(R.drawable.icon_male)
        } else {
            binding.textGender.text = RMKey.TYPE_FEMALE
            binding.iconGender.setImageResource(R.drawable.icon_female)
        }

        binding.textOrigin.text = "Name: ${data.origin.name}"

        binding.group2.setBackgroundResource(data.getBackgroundStatus())
        binding.typeStatus.setImageResource(data.getIconStatus())
        binding.textStatusType.text = data.getStatus(this)
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(CharacterViewModel::class.java)

        viewModel.character.observe(this, Observer { state ->
            when (state) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    state.data?.let {
                        updateUI(it)
                    }
                }
                else -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun showLoading() {
        binding.loading.playAnimation()
        binding.loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.loading.visibility = View.GONE
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