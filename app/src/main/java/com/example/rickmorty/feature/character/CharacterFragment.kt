package com.example.rickmorty.feature.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.data.model.Character
import com.example.rickmorty.app.data.utils.Resource
import com.example.rickmorty.app.data.utils.SpacesItemDecoration
import com.example.rickmorty.app.data.utils.adapter.CharacterAdapter
import com.example.rickmorty.databinding.FragmentCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterFragment : BaseFragment(),CustomState{
    private lateinit var binding : FragmentCharacterBinding

    @Inject
    lateinit var viewModelFactory : CharacterViewModelFactory
    private lateinit var vm : CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        vm.getCharactersData()
        initUI()
    }

    override fun initUI() {
        vm.characters.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading ->{
                    showLoading()
                }
                is Resource.Success ->{
                    hideLoading()
                    it.data?.results?.let { items->
                       vm.list.addAll(items)
                    }
                    updateUI(vm.list)
                }
                else ->{
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun updateUI(itemList : ArrayList<Character>){
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        val itemDec = SpacesItemDecoration(16)
        val adapter = CharacterAdapter(itemList)
        binding.recyclerView.addItemDecoration(itemDec)
        binding.recyclerView.adapter = adapter
    }

    override fun initViewModel() {
        vm = ViewModelProvider(this,viewModelFactory).get(CharacterViewModel::class.java)
    }

    override fun showLoading() {
        binding.loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.loading.visibility = View.GONE
    }

    companion object{
        @JvmStatic
        fun newInstance() = CharacterFragment().apply {

        }
    }

}