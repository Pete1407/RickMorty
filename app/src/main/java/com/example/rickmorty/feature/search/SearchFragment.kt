package com.example.rickmorty.feature.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.databinding.FragmentSearchBinding
import com.example.rickmorty.feature.character.CharacterViewModel

class SearchFragment : BaseFragment(),CustomState {

    private lateinit var binding : FragmentSearchBinding
    private var searchText : String = ""
    private var viewModel : CharacterViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSearchBinding.inflate(inflater,container,false)
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

    override fun initListener() {

    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        // TODO: use Coroutine Flow
    }

    override fun showLoading() {
        binding.loading.showLoading()
    }

    override fun hideLoading() {
        binding.loading.hideLoading()
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String){

        }
    }

}