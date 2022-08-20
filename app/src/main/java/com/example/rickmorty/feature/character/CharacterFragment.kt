package com.example.rickmorty.feature.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.feature.character.CharacterViewModel.BaseState
import com.example.rickmorty.app.data.utils.adapter.CharacterAdapter
import com.example.rickmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.example.rickmorty.app.data.utils.extension.gone
import com.example.rickmorty.app.data.utils.extension.visible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


@AndroidEntryPoint
class CharacterFragment : BaseFragment(),CustomState{
    private lateinit var binding : FragmentCharacterBinding

    @Inject
    lateinit var viewModelFactory : CharacterViewModelFactory
    private lateinit var viewModel : CharacterViewModel
    private var adapter : CharacterAdapter?= null

    private val human = RMKey.TYPE_HUMAN
    private val alien = RMKey.TYPE_ALIEN
    private val animal = RMKey.TYPE_ANIMAL
    private val unknown = RMKey.TYPE_UNKNOWN

    private var humanList = mutableListOf<Character>()
    private var alienList = mutableListOf<Character>()
    private var animalList = mutableListOf<Character>()
    private var unknownList = mutableListOf<Character>()
    private var allList = mutableListOf<Character>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val job = Job()
        
        viewModel.getCharacterBySpecies()
        viewModel.getCharacterBySpecies(human)
        viewModel.getCharacterBySpecies(alien)
        viewModel.getCharacterBySpecies(animal)
        viewModel.getCharacterBySpecies(unknown)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initUI()
    }

    override fun initUI() {
        if (adapter == null) {
            adapter = CharacterAdapter(arrayListOf())
        }
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this,viewModelFactory).get(CharacterViewModel::class.java)
        viewModel._uiState.observe(viewLifecycleOwner,state)

    }

    override fun showLoading() {
        binding.loading.playAnimation()
        binding.loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.loading.visibility = View.GONE
    }

    private val state = Observer<CharacterViewModel.BaseState>{
        when(it){
            is BaseState.Loading -> {
                showLoading(it.isLoad)
            }
            is BaseState.Success ->{
                Log.d(RMKey.TAG,"total Size --> ${it.data.size}")
                Log.d(RMKey.TAG,"${it.data}")
            }
            else -> {
                // error
            }
        }
    }

    private fun showLoading(isLoad : Boolean) {
        binding.loading.apply {
            if (isLoad) {
                this.visible()
                this.playAnimation()
            } else {
                this.gone()
                this.pauseAnimation()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object{
        @JvmStatic
        fun newInstance() = CharacterFragment().apply {

        }
    }
}