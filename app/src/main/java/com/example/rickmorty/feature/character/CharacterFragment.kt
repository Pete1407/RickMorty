package com.example.rickmorty.feature.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickmorty.base.BaseFragment
import com.example.rickmorty.base.CustomState
import com.example.rickmorty.databinding.FragmentCharacterBinding

class CharacterFragment : BaseFragment(),CustomState{
    private lateinit var binding : FragmentCharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentCharacterBinding.inflate(inflater,container,false)
        return view.root
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
        fun newInstance() = CharacterFragment().apply {

        }
    }

}