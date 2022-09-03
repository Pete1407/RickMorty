package com.example.rickmorty.feature.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.databinding.FragmentEpisodeBinding

class EpisodeFragment : BaseFragment(),CustomState {
    private lateinit var binding : FragmentEpisodeBinding

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
        initUI()
    }

    override fun initUI() {

    }

    override fun initViewModel() {

    }

    override fun initListener() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    companion object{
        @JvmStatic
        fun newInstance()=EpisodeFragment().apply {

        }
    }

}