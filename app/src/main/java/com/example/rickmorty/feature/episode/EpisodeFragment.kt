package com.example.rickmorty.feature.episode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Episodes
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.data.utils.adapter.LocationAdapter
import com.example.rickmorty.databinding.FragmentEpisodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment(),CustomState {
    private lateinit var binding : FragmentEpisodeBinding

    private var adapter : LocationAdapter? = null
    private lateinit var viewModel : EpisodeViewModel
    private var episodes : Episodes? = null

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
        viewModel.getAllEpisode()
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

    fun setRecyclerViewAdapter(){

    }

    private val allEpisode = Observer<Resource<Episodes>>{
        when(it){
            is Resource.Loading -> {showLoading()}
            is Resource.Success ->{
                hideLoading()
                this.episodes = it.data

            }
            is Resource.Error ->{

            }
        }
    }

    companion object{
        @JvmStatic
        fun newInstance()=EpisodeFragment().apply {

        }
    }

}