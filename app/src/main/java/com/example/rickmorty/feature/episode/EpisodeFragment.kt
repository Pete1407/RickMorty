package com.example.rickmorty.feature.episode

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
import com.example.rickmorty.app.data.model.Episode
import com.example.rickmorty.app.data.model.Episodes
import com.example.rickmorty.app.data.model.SeasonModel
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.data.utils.adapter.EpisodesAdapter
import com.example.rickmorty.databinding.FragmentEpisodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment(),CustomState {

    private lateinit var binding : FragmentEpisodeBinding
    private lateinit var viewModel : EpisodeViewModel
    private var episodes : Episodes? = null
    private var adapter : EpisodesAdapter? = null

    private var allEpisodeSeason = ArrayList<Episode>()
    private var season1 = ArrayList<SeasonModel>()
    private var season2 = ArrayList<SeasonModel>()
    private var season3 = ArrayList<SeasonModel>()
    private var season4 = ArrayList<SeasonModel>()

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

    private val allEpisode = Observer<Resource<Episodes>>{
        when(it){
            is Resource.Loading -> {showLoading()}
            is Resource.Success ->{
                hideLoading()
                episodes = it.data
                if(episodes?.info?.prev.isNullOrEmpty() && episodes?.info?.getNextPageFromLink()?.toInt() == 2){
                    viewModel.getAllEpisode(episodes?.info?.getNextPageFromLink())
                }else if(episodes?.info?.next != null && episodes?.info?.prev != null){
                    viewModel.getAllEpisode(episodes?.info?.getNextPageFromLink())
                }
                allEpisodeSeason.addAll(episodes?.results!!)
                Log.d("size","${allEpisodeSeason.size}")
            }
            is Resource.Error ->{

            }
        }
    }

    private fun setAllEpisodeIntoSpecifySeason(allEpisode : Episodes){
        allEpisode.results.forEach {
            val splitedText = it.episode.split("E")
            val seasonText = splitedText[0]
            val episodeText = splitedText[1]
            //Log.d(RMKey.DEBUG_TAG,"$seasonText $episodeText")
            //val episodeBySeasonModel = EpisodeBySeasonModel(seasonText,episodeText,allEpisode.)

        }
    }

    fun convertEpisodesData(){

    }

    companion object{
        @JvmStatic
        fun newInstance()=EpisodeFragment().apply {

        }
    }

}