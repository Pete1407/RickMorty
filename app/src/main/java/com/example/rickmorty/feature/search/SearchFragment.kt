package com.example.rickmorty.feature.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.data.model.Characters
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.data.utils.ToastView
import com.example.rickmorty.app.data.utils.adapter.CharacterAdapter
import com.example.rickmorty.app.data.utils.extension.gone
import com.example.rickmorty.app.data.utils.extension.visible
import com.example.rickmorty.databinding.FragmentSearchBinding
import com.example.rickmorty.feature.character.CharacterViewModel
import com.example.rickmorty.feature.character.DetailCharacterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment(),CustomState {

    private lateinit var binding : FragmentSearchBinding
    private var charactersObj : Characters? = null
    private var navController : NavController? = null
    private var adapter : CharacterAdapter? = null
    private lateinit var viewModel : CharacterViewModel

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
        navController = Navigation.findNavController(binding.root)
        initViewModel()
        initListener()
        initUI()
    }

    override fun initUI() {
        if(adapter == null){
            adapter = CharacterAdapter(arrayListOf())
            adapter!!.setEventClickDetailListener {
                DetailCharacterActivity.create(requireContext(),it)
//               val bundle = bundleOf("character" to it)
//               navController?.navigate(R.id.action_global_detailCharacterActivity,bundle)
            }
        }
        binding.recylcerView.adapter = adapter

    }

    override fun initListener() {
        binding.searchBarWidget.setEventSearchListener {
            viewModel.getCharactersBySearchingName(it)
        }

    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        viewModel.searchResultData.observe(viewLifecycleOwner,state)
        //viewModel.errorData.observe(viewLifecycleOwner,errorValue)
    }

    override fun showLoading() {
        binding.loading.showLoading()
    }

    override fun hideLoading() {
        binding.loading.hideLoading()
    }

    val state = Observer<Resource<Characters>>{
        when(it){
            is Resource.Success ->{
                hideLoading()
                binding.notFound.gone()
                it.data?.let { result ->
                    charactersObj = result
                    adapter?.refreshAllData(charactersObj!!.results)
                }
            }
            is Resource.Loading ->{
                showLoading()
            }
            else ->{
                  ToastView(requireContext()).showLongToast(it.message.toString())
//                binding.notFound.visible()
//                binding.recylcerView.gone()
            }
        }
    }

    private val errorValue = Observer<String> {
        Log.e("error",it)
        if(!it.isNullOrBlank()){
            binding.notFound.visible()
            binding.recylcerView.gone()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String){

        }
    }

}