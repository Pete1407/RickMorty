package com.example.rickmorty.feature.character.info_character

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseActivity
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.base.RmKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.databinding.ActivityInfoCharacterBinding
import com.example.rickmorty.feature.character.CharacterViewModel
import com.example.rickmorty.feature.character.CharacterViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import okio.JvmStatic
import javax.inject.Inject

@AndroidEntryPoint
class InfoCharacterActivity : BaseActivity(),CustomState {
    private lateinit var binding : ActivityInfoCharacterBinding
    private var characterChosen : Character? = null

    @Inject
    lateinit var viewModelFactory : CharacterViewModelFactory

    private lateinit var viewModel : CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoCharacterBinding.inflate(layoutInflater)
        characterChosen = intent.getParcelableExtra<Character>(RmKey.ITEM_CHARACTER) as Character
        val view = binding.root
        setContentView(view)
        initUI()
        initViewModel()
        viewModel.getSingleCharacter(characterChosen?.id.toString())
    }

    override fun onResume() {
        super.onResume()
    }

    override fun initUI() {
        characterChosen?.let{
            binding.toolBar.setTextTitle(it.name)
        }?:kotlin.run {
            binding.toolBar.setTextTitle(resources.getString(R.string.info_character))
        }
        binding.toolBar.setOnClickListener {
            finish()
        }
    }

    private fun updateUI(data : Character){
        Log.i("result","name: ${data.name}")
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this,viewModelFactory).get(CharacterViewModel::class.java)

        viewModel.character.observe(this, Observer { state ->
            when(state){
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    state.data?.let {
                        updateUI(it)
                    }
                }
                else ->{
                    Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun showLoading() {
        binding.loading.visibility = View.VISIBLE
        binding.loading.playAnimation()
    }

    override fun hideLoading() {
        binding.loading.visibility = View.GONE
    }

    companion object{
        const val ITEM_CHARACTER = "item-character"

        @JvmStatic
        fun create(context : Context,item : Character){
            context.startActivity(Intent(context,InfoCharacterActivity::class.java).apply {
                putExtra(ITEM_CHARACTER,item)
            })
        }
    }
}