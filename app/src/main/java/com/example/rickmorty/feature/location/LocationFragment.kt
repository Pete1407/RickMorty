package com.example.rickmorty.feature.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.base.RMKey
import com.example.rickmorty.app.data.model.Info
import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.app.data.model.Locations
import com.example.rickmorty.app.data.utils.GridSpacingItemDecoration
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.data.utils.adapter.LocationAdapter
import com.example.rickmorty.databinding.FragmentLocationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.roundToInt


@AndroidEntryPoint
class LocationFragment : BaseFragment(),CustomState{
    private lateinit var binding : FragmentLocationBinding
    private var adapter : LocationAdapter? = null
    private var locationList = ArrayList<Location>()

    private var info : Info? = null
    private var totalPage : Int = 0
    private var pastVisibleItem = 0

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
        if(adapter == null){
            adapter = LocationAdapter(arrayListOf())
        }
        val spanCount = 2
        val space = resources.getDimensionPixelOffset(R.dimen.spacing_small)
        val includeEdge = false
        binding.recyclerView.addItemDecoration(GridSpacingItemDecoration(spanCount,space,includeEdge))
        binding.recyclerView.adapter = adapter
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

    override fun initListener() {
        binding.refreshLayout.setOnRefreshListener {
            refreshList()
        }
        binding.recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = binding.recyclerView.layoutManager!!.childCount
                val firstVisibleItems =  (binding.recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()
                val totalItem = binding.recyclerView.layoutManager!!.itemCount

                    if((firstVisibleItems + visibleItemCount) >= totalItem && locationList.size > 0 && !viewModel.isLoading){
                        viewModel.getAllLocation(info?.getNextPageFromLink())
                    }

            }
        })
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun refreshList(){
        showLoading()
        binding.refreshLayout.isRefreshing = false
        viewModel.getAllLocation()
    }

    private val allLocationState = Observer<Resource<Locations>>{
        when(it){
            is Resource.Loading -> {
                showLoading()
            }
            is Resource.Success -> {
                hideLoading()
                val result = it.data
                info = result?.info
                Log.d(RMKey.DEBUG_TAG,"NextPage is ${info?.getNextPageFromLink()} and PrevPage is ${info?.getPreviousPageFromLink()?:"none"} and now is ")
                locationList = ArrayList(result!!.results)
                if(info?.prev.isNullOrEmpty() && info?.next!=null){
                    totalPage = info!!.pages!!
                    adapter?.refreshList(locationList)
                }
                else{
                    adapter?.addNewItems(locationList)
                }
            }
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = LocationFragment().apply {}
    }

}