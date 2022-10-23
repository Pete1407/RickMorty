package com.example.rickmorty.feature.episode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.data.model.Episode
import com.example.rickmorty.app.data.model.Episodes
import com.example.rickmorty.app.data.model.SeasonModel
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.data.utils.adapter.EpisodesAdapter
import com.example.rickmorty.app.data.utils.extension.visible
import com.example.rickmorty.databinding.FragmentEpisodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment(),CustomState {

    private lateinit var binding : FragmentEpisodeBinding
    private lateinit var viewModel : EpisodeViewModel
    private var episodes : Episodes? = null
    private var adapter : EpisodesAdapter? = null

    private var allEpisodeSeason = ArrayList<Episode>()
    private var seasonCollection = ArrayList<SeasonModel>()
    private var season1 : SeasonModel? = null
    private var season2 : SeasonModel? = null
    private var season3 : SeasonModel? = null
    private var season4 : SeasonModel? = null
    private var season5 : SeasonModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initListener()
        initUI()
    }

    override fun initUI() {
        setRecyclerViewAdapter()
        viewModel.getAllEpisode(null)
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(EpisodeViewModel::class.java)
        viewModel.episodesData.observe(viewLifecycleOwner,allEpisode)
    }

    override fun initListener() {
        binding.refreshLayout.setOnRefreshListener {
            refreshList()
        }
    }

    override fun showLoading() {
        binding.loading.showLoading()
    }

    override fun hideLoading() {
        binding.loading.hideLoading()
    }

    private fun setRecyclerViewAdapter(){
        if(adapter == null){
            adapter = EpisodesAdapter(arrayListOf())
        }
        binding.recyclerView.adapter = adapter
    }

    private fun refreshList(){
        episodes = null
        showLoading()
        seasonCollection.clear()
        allEpisodeSeason.clear()
        viewModel.getAllEpisode(null)
    }

    private val allEpisode = Observer<Resource<Episodes>>{
        when(it){
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Success ->{
                //hideLoading()
                episodes = it.data
                allEpisodeSeason.addAll(episodes?.results!!)
                if(episodes?.info?.prev.isNullOrEmpty() && episodes?.info?.getNextPageFromLink()?.toInt() == 2){
                    viewModel.getAllEpisode(episodes?.info?.getNextPageFromLink())
                }else if(episodes?.info?.next != null && episodes?.info?.prev != null){
                    viewModel.getAllEpisode(episodes?.info?.getNextPageFromLink())
                }else{
                    hideLoading()
                    binding.refreshLayout.isRefreshing = false
                    setAllEpisodeIntoSpecifySeason(allEpisodeSeason)
                }
            }
            is Resource.Error ->{
                //Log.e("error",it.)
            }
        }
    }

    private fun setAllEpisodeIntoSpecifySeason(list : ArrayList<Episode>){
        binding.refreshLayout.isRefreshing = false
        convertEpisodesData(list)
    }

    private fun convertEpisodesData(list : ArrayList<Episode>){
        val epListBySeasonOne = ArrayList<Episode>()
        val epListBySeasonTwo = ArrayList<Episode>()
        val epListBySeasonThree = ArrayList<Episode>()
        val epListBySeasonFour = ArrayList<Episode>()
        val epListBySeasonFive = ArrayList<Episode>()
        list.forEach {
            val fullEpText = it.episode.split("E")
            val ssText = fullEpText[0]
            //val epText = fullEpText[1]
            when(ssText[2]){
                '1' ->{
                    epListBySeasonOne.add(it)
                }
                '2' ->{
                    epListBySeasonTwo.add(it)
                }
                '3' ->{
                    epListBySeasonThree.add(it)
                }
                '4' ->{
                    epListBySeasonFour.add(it)
                }
                else ->{
                    epListBySeasonFive.add(it)
                }
            }
        }
        season1 = SeasonModel("Season 1",epListBySeasonOne)
        season2 = SeasonModel("Season 2",epListBySeasonTwo)
        season3 = SeasonModel("Season 3",epListBySeasonThree)
        season4 = SeasonModel("Season 4",epListBySeasonFour)
        season5 = SeasonModel("Season 5",epListBySeasonFive)
        seasonCollection.add(season1!!)
        seasonCollection.add(season2!!)
        seasonCollection.add(season3!!)
        seasonCollection.add(season4!!)
        seasonCollection.add(season5!!)
        adapter?.refreshList(seasonCollection)
    }

    companion object{
        @JvmStatic
        fun newInstance()=EpisodeFragment().apply {

        }
    }
//        Log.d("sizeEpisode","SS1 --> ${epListBySeasonOne.size}")
//        Log.d("sizeEpisode","SS2 --> ${epListBySeasonTwo.size}")
//        Log.d("sizeEpisode","SS3 --> ${epListBySeasonThree.size}")
//        Log.d("sizeEpisode","SS4 --> ${epListBySeasonFour.size}")
//        Log.d("sizeEpisode","SS5 --> ${epListBySeasonFive.size}")
}