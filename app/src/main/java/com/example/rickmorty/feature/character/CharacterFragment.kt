package com.example.rickmorty.feature.character

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.LayoutDirection
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.model.Info
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.data.utils.adapter.CharactersAdapter
import com.example.rickmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.*


@AndroidEntryPoint
class CharacterFragment : BaseFragment(),CustomState{
    private lateinit var binding : FragmentCharacterBinding

    @Inject
    lateinit var viewModelFactory : CharacterViewModelFactory
    private lateinit var viewModel : CharacterViewModel
    private var adapter : CharactersAdapter?= null

    private var humanList = mutableListOf<Character>()
    private var alienList = mutableListOf<Character>()
    private var animalList = mutableListOf<Character>()
    private var unknownList = mutableListOf<Character>()
    private var allList = ArrayList<Character>()

    private var numberPage : String = ""
    private var info : Info? = null


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
        refreshList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initViewModel()
        initUI()

    }

    override fun initListener() {

        binding.refreshLayout.setOnRefreshListener {
            refreshList()
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = binding.recyclerView.layoutManager!!.childCount
                val pastVisibleItems =  (binding.recyclerView.layoutManager as GridLayoutManager).findFirstCompletelyVisibleItemPosition()
                val totalItem = binding.recyclerView.layoutManager!!.itemCount
                if((visibleItemCount + pastVisibleItems) >= totalItem && allList.size > 0 && !viewModel.isLoading){
                    viewModel.getCharacterByAllSpecies(numberPage)
                }
            }
        })
    }

    override fun initUI() {
        if (adapter == null) {
            adapter = CharactersAdapter(
                requireContext(),
                arrayListOf(),
                arrayListOf(),
                arrayListOf(),
                arrayListOf(),
                arrayListOf()
            )

        }
        val layoutMng = GridLayoutManager(requireContext(),2)
        layoutMng.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int {
                return if(position > 4 ){
                    1
                }else{
                    2
                }
            }

        }
        binding.recyclerView.layoutManager = layoutMng
        binding.recyclerView.adapter = adapter
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this,viewModelFactory).get(CharacterViewModel::class.java)
        viewModel.allData.observe(viewLifecycleOwner,allState)
        viewModel.humanData.observe(viewLifecycleOwner,humanState)
        viewModel.alienData.observe(viewLifecycleOwner,alienState)
        viewModel.animalData.observe(viewLifecycleOwner,animalState)
        viewModel.unknownData.observe(viewLifecycleOwner,unknownState)
        viewModel.error.observe(viewLifecycleOwner,errorState)
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

    private val humanState = Observer<Resource<Characters>>{
        when(it){
            is Resource.Loading -> {}
            is Resource.Success ->{
                humanList = shuffleItem(it.data!!.results)
                adapter?.refreshHumanList(humanList)
            }else -> {}
        }
    }

    private val alienState = Observer<Resource<Characters>>{
        when(it){
            is Resource.Loading -> {}
            is Resource.Success ->{
                alienList = shuffleItem(it.data!!.results)
                adapter?.refreshAlienList(alienList)
            }else -> {}
        }
    }

    private val animalState = Observer<Resource<Characters>>{
        when(it){
            is Resource.Loading -> {}
            is Resource.Success ->{
                animalList = shuffleItem(it.data!!.results)
                adapter?.refreshAnimalList(animalList)
            }else -> {}
        }
    }

    private val unknownState = Observer<Resource<Characters>>{
        when(it){
            is Resource.Loading -> {}
            is Resource.Success ->{
                unknownList = shuffleItem(it.data!!.results)
                adapter?.refreshUnknownList(unknownList)
            }else -> {}
        }
    }

    private val allState = Observer<Resource<Characters>> {
        when (it) {
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Success -> {
                hideLoading()
                allList = ArrayList<Character>(it.data!!.results)
                info = it.data.info
                Log.d(RMKey.DEBUG_TAG,info.toString())
                numberPage = getNextPageFromLink(info?.next)
                if(info?.prev.isNullOrEmpty() && info?.next!=null){
                    adapter?.refreshAllList(allList)
                }else{
                    adapter?.addNewItems(allList)
                }

            }
            else-> {}
        }
    }

    private fun getNextPageFromLink(next : String?):String{
        val nextPageUri = Uri.parse(next)
        val numberNextPage = nextPageUri.getQueryParameter("page")
        return numberNextPage.toString()
    }

    override fun showLoading() {
        binding.loading.showLoading()
    }

    override fun hideLoading() {
        binding.loading.hideLoading()
    }

    private val errorState = Observer<String?> {
        if(it!= null){
            Toast.makeText(requireContext(), it.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    private fun shuffleItem(list : List<Character>):MutableList<Character>{
        val dataShuffled = list.shuffled()
        return dataShuffled.subList(0,10).toMutableList()
    }

    private fun refreshList(){
        binding.refreshLayout.isRefreshing = false
        numberPage = ""
        info = null
        allList.clear()
        alienList.clear()
        animalList.clear()
        humanList.clear()
        unknownList.clear()
        viewModel.getCharacterByAllSpecies()
        viewModel.getCharacterByAlienSpecies()
        viewModel.getCharacterByAnimalSpecies()
        viewModel.getCharacterByHumanSpecies()
        viewModel.getCharacterByUnknownSpecies()
    }

    companion object{
        @JvmStatic
        fun newInstance() = CharacterFragment().apply {

        }
    }
}