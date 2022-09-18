package com.example.rickmorty.feature.location

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.rickmorty.app.base.BaseActivity
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.data.model.Location
import com.example.rickmorty.databinding.ActivityDetailLocationBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailLocationActivity : BaseActivity(),CustomState {

    private var location : Location? = null
    @Inject
    lateinit var viewModelFactory : LocationViewModelFactory
    private var viewModel : LocationViewModel? = null

    private val binding : ActivityDetailLocationBinding by lazy{
         ActivityDetailLocationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        location = intent.getParcelableExtra(LOCATION_PARAM)
        initViewModel()
        initListener()
        initUI()
    }

    override fun initUI() {
        location?.let {
            binding.locationName.text = it.name
            binding.dimensionText.text = it.dimension
            binding.typeText.text = it.type
        }

    }

    override fun initListener() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this,viewModelFactory).get(LocationViewModel::class.java)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    companion object{
        private const val LOCATION_PARAM = "location_param"
        fun start(context : Context,itemSelected : Location){
            val intent = Intent(context,DetailLocationActivity::class.java).apply {
                putExtra(LOCATION_PARAM,itemSelected)
            }
            context.startActivity(intent)
        }
    }
}