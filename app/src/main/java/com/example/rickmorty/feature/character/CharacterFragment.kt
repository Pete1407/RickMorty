package com.example.rickmorty.feature.character

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.feature.character.CharacterViewModel.BaseState
import com.example.rickmorty.app.data.utils.adapter.CharacterAdapter
import com.example.rickmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.example.rickmorty.app.data.utils.extension.gone
import com.example.rickmorty.app.data.utils.extension.visible
import kotlinx.coroutines.*
import kotlin.concurrent.thread


@AndroidEntryPoint
class CharacterFragment : BaseFragment(),CustomState{
    private lateinit var binding : FragmentCharacterBinding

    @Inject
    lateinit var viewModelFactory : CharacterViewModelFactory
    private lateinit var viewModel : CharacterViewModel
    private var adapter : CharacterAdapter?= null

    private var humanList = mutableListOf<Character>()
    private var alienList = mutableListOf<Character>()
    private var animalList = mutableListOf<Character>()
    private var unknownList = mutableListOf<Character>()
    private var allList = ArrayList<Character>()

    // TODO: แก้ใหม่ 
    private var job : Job = Job()

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
        // TODO: แก้ใหม่ 
        val uiScope = CoroutineScope(Dispatchers.IO+ job)
        uiScope.launch(Dispatchers.IO) {
            viewModel.getCharacterByAllSpecies()
            viewModel.getCharacterByAlienSpecies()
            viewModel.getCharacterByAnimalSpecies()
            viewModel.getCharacterByHumanSpecies()
            viewModel.getCharacterByUnknownSpecies()
        }

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
        viewModel.allData.observe(viewLifecycleOwner,allState)
//        viewModel.humanData.observe(viewLifecycleOwner,humanState)
//        viewModel.alienData.observe(viewLifecycleOwner,alienState)
//        viewModel.animalData.observe(viewLifecycleOwner,animalState)
//        viewModel.unknownData.observe(viewLifecycleOwner,unknownState)
        viewModel.error.observe(viewLifecycleOwner,errorState)
    }

    override fun showLoading() {
        binding.loading.visibility = View.VISIBLE
        binding.loading.playAnimation()

    }

    override fun hideLoading() {
        binding.loading.visibility = View.GONE
        binding.loading.pauseAnimation()
    }

    private fun showLoadingProgress(isLoad : Boolean) {
        if(isLoad){
            showLoading()
        }else{
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                hideLoading()
                Log.d(RMKey.DEBUG_TAG,"Tag for show loading")
            },2000)
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    private val allState = Observer<Resource<Characters>> {
        when (it) {
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Success -> {
                hideLoading()
                val list = ArrayList<Character>(it.data!!.results)
                allList.addAll(list)
                Log.d(RMKey.DEBUG_TAG,"Size --> ${allList.size.toString()}")
            }
            else-> {}
        }
    }

//    private val humanState = Observer<BaseState>{
//        when(it){
//            is BaseState.Loading -> {}
//            is BaseState.Success ->{
//                Log.d(RMKey.DEBUG_TAG,"### HUMAN ###")
//                Log.d(RMKey.DEBUG_TAG,"total Size --> ${it.data.size}")
//                Log.d(RMKey.DEBUG_TAG,"${it.data}")
//            }else -> {}
//        }
//    }

//    private val alienState = Observer<BaseState>{
//        when(it){
//            is BaseState.Loading -> {}
//            is BaseState.Success ->{
//                Log.d(RMKey.DEBUG_TAG,"total Size --> ${it.data.size}")
//                Log.d(RMKey.DEBUG_TAG,"${it.data}")
//            }else -> {}
//        }
//    }

//    private val animalState = Observer<BaseState>{
//        when(it){
//            is BaseState.Loading -> {}
//            is BaseState.Success ->{
//                Log.d(RMKey.DEBUG_TAG,"total Size --> ${it.data.size}")
//                Log.d(RMKey.DEBUG_TAG,"${it.data}")
//            }else -> {}
//        }
//    }

//    private val unknownState = Observer<BaseState>{
//        when(it){
//            is BaseState.Loading -> {}
//            is BaseState.Success ->{
//                Log.d(RMKey.DEBUG_TAG,"total Size --> ${it.data.size}")
//                Log.d(RMKey.DEBUG_TAG,"${it.data}")
//            }else -> {}
//        }
//    }

    private val errorState = Observer<String?> {
        if(it!= null){
            Toast.makeText(requireContext(),"${it.toString()}",Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = CharacterFragment().apply {

        }
    }
}