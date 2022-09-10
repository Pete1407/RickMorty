package com.example.rickmorty.feature.location

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
import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.app.data.model.Locations
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.data.utils.adapter.LocationAdapter
import com.example.rickmorty.databinding.FragmentLocationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LocationFragment : BaseFragment(),CustomState{
    private lateinit var binding : FragmentLocationBinding
    private var adapter : LocationAdapter? = null

    @Inject
    lateinit var viewModelFactory: LocationViewModelFactory
    private lateinit var viewModel : LocationViewModel

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
        initListener()
        initViewModel()
        initUI()
    }

    override fun initUI() {
        adapter = LocationAdapter(arrayListOf())
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this,viewModelFactory).get(LocationViewModel::class.java)
        viewModel.locationData.observe(viewLifecycleOwner,allLocationState)
    }

    override fun showLoading() {
        binding.loading.showLoading()
    }

    override fun hideLoading() {
        binding.loading.hideLoading()
    }

    override fun initListener() {}

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun refreshList(){
        //binding.refreshLayout.isRefreshing = false
        viewModel.getAllLocation()
    }

    private val allLocationState = Observer<Resource<Locations>>{
        when(it){
            is Resource.Success -> {
                val result = it.data
                Log.d(RMKey.DEBUG_TAG,result.toString())
            }
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = LocationFragment().apply {

        }
    }

}