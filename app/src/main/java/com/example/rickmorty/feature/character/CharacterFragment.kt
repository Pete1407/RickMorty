package com.example.rickmorty.feature.character

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.UiThread
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
import com.example.rickmorty.app.data.utils.GridSpacingItemDecoration
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.data.utils.SpacesItemDecoration
import com.example.rickmorty.app.data.utils.ToastView
import com.example.rickmorty.app.data.utils.adapter.CharactersAdapter
import com.example.rickmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.*
import kotlinx.coroutines.Runnable

// character Page
@AndroidEntryPoint
class CharacterFragment : BaseFragment(),CustomState{
    private lateinit var binding : FragmentCharacterBinding

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
        //refreshList()
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
                    viewModel.getCharacterByAllSpecies(info?.getNextPageFromLink())
                }
            }
        })
    }

    override fun initUI() {
        refreshList()
        if (adapter == null) {
            adapter = CharactersAdapter(
                requireContext(),
                arrayListOf(),
                arrayListOf(),
                arrayListOf(),
                arrayListOf(),
                arrayListOf()
            )
            adapter!!.setEventClickDetailListener { characterData ->
                DetailCharacterActivity.create(requireContext(),characterData)
            }

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
        binding.recyclerView.addItemDecoration(SpacesItemDecoration(20))
        binding.recyclerView.adapter = adapter
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        viewModel.allData.observe(viewLifecycleOwner,allState)
        viewModel.humanData.observe(viewLifecycleOwner,humanState)
        viewModel.alienData.observe(viewLifecycleOwner,alienState)
        viewModel.animalData.observe(viewLifecycleOwner,animalState)
        viewModel.unknownData.observe(viewLifecycleOwner,unknownState)
    }

    private val humanState = Observer<Resource<Characters>>{
        when(it){
            is Resource.Loading -> {}
            is Resource.Success ->{
                it.data?.let { result ->
                    adapter?.refreshHumanList(shuffleItem(result.results))
                }
            }else -> {
                ToastView(requireContext()).showShortToast(it.message.toString())
            }
        }
    }

    private val alienState = Observer<Resource<Characters>>{
        when(it){
            is Resource.Loading -> {}
            is Resource.Success ->{
                it.data?.let { result ->
                    adapter?.refreshAlienList(shuffleItem(result.results))
                }
            }else -> {
                ToastView(requireContext()).showShortToast(it.message.toString())
            }
        }
    }

    private val animalState = Observer<Resource<Characters>>{
        when(it){
            is Resource.Loading -> {}
            is Resource.Success ->{
                it.data?.let { result ->
                    adapter?.refreshAnimalList(shuffleItem(result.results))
                }

            }else -> {
                ToastView(requireContext()).showShortToast(it.message.toString())
            }
        }
    }

    private val unknownState = Observer<Resource<Characters>>{
        when(it){
            is Resource.Loading -> {}
            is Resource.Success ->{
                it.data?.let { result ->
                    adapter?.refreshUnknownList(shuffleItem(result.results))
                }

            }else -> {
                ToastView(requireContext()).showShortToast(it.message.toString())
            }
        }
    }

    private val allState = Observer<Resource<Characters>> {
        when (it) {
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Success -> {
                hideLoading()
                it.data?.let { result ->
                    allList = ArrayList(result.results)
                    info = result.info
                    Log.d(RMKey.DEBUG_TAG,info.toString())
                    //numberPage = getNextPageFromLink(info?.next)
                    if(info?.prev.isNullOrEmpty() && info?.getNextPageFromLink()?.toInt() == 2){
                        adapter?.refreshAllList(allList)
                    }else{
                        adapter?.addNewItems(allList)
                    }
                }
            }
        }
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
        clearData()
        binding.refreshLayout.isRefreshing = false
        viewModel.getCharacterByHumanSpecies()
        viewModel.getCharacterByAlienSpecies()
        viewModel.getCharacterByAnimalSpecies()
        viewModel.getCharacterByUnknownSpecies()
        viewModel.getCharacterByAllSpecies()
    }

    private fun clearData(){
        numberPage = ""
        info = null
        allList.clear()
        alienList.clear()
        animalList.clear()
        humanList.clear()
        unknownList.clear()
    }

    companion object{
        @JvmStatic
        fun newInstance() = CharacterFragment().apply {

        }
    }
}