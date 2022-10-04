package com.example.rickmorty.feature.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.databinding.FragmentEpisodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment(),CustomState {
    private lateinit var binding : FragmentEpisodeBinding

    private lateinit var viewModel : EpisodeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(EpisodeViewModel::class.java)
       // viewModel.episodesData.observe()
    }

    override fun initListener() {

    }

    override fun showLoading() {
        binding.loading.showLoading()
    }

    override fun hideLoading() {
        binding.loading.hideLoading()
    }

    companion object{
        @JvmStatic
        fun newInstance()=EpisodeFragment().apply {

        }
    }

}