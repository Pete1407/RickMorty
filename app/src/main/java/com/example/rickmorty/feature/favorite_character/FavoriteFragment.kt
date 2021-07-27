package com.example.rickmorty.feature.favorite_character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickmorty.base.BaseFragment
import com.example.rickmorty.base.CustomState
import com.example.rickmorty.databinding.FragmentFavorBinding

class FavoriteFragment : BaseFragment(),CustomState {

    private lateinit var binding : FragmentFavorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavorBinding.inflate(inflater,container,false)
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

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    companion object{
        @JvmStatic
        fun newInstance()=FavoriteFragment().apply {

        }
    }

}