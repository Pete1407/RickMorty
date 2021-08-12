package com.example.rickmorty.feature.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickmorty.base.BaseFragment
import com.example.rickmorty.base.CustomState
import com.example.rickmorty.databinding.FragmentCharacterBinding
import com.example.rickmorty.databinding.FragmentLocationBinding

class LocationFragment : BaseFragment(),CustomState{
    private lateinit var binding : FragmentLocationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentLocationBinding.inflate(layoutInflater,container,false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        fun newInstance() = LocationFragment().apply {

        }
    }

}