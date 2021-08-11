package com.example.rickmorty.feature

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.rickmorty.R
import com.example.rickmorty.base.BaseActivity
import com.example.rickmorty.base.BaseFragment
import com.example.rickmorty.databinding.ActivityMainBinding
import com.example.rickmorty.feature.character.CharacterFragment
import com.example.rickmorty.feature.episode.EpisodeFragment
import com.example.rickmorty.feature.favorite_character.FavoriteFragment
import com.example.rickmorty.feature.location.LocationFragment
import com.example.rickmorty.feature.adapter.PagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity(),BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding : ActivityMainBinding
    private var view : View? = null
    private var list = ArrayList<BaseFragment>()
    private lateinit var pager : PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)
        initUI()
    }

    private fun initUI(){
       setMenuBottomNavigation()
       binding.bottomNav.setOnNavigationItemSelectedListener(this)
    }

    private fun setMenuBottomNavigation(){
        pager = PagerAdapter(supportFragmentManager)
        list.add(CharacterFragment.newInstance())
        list.add(LocationFragment.newInstance())
        list.add(EpisodeFragment.newInstance())
        list.add(FavoriteFragment.newInstance())
        pager.setPageItems(list)
        binding.viewPager.adapter = pager
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.character ->{
                binding.viewPager.currentItem = 0
                return true
            }
            R.id.location ->{
                binding.viewPager.currentItem = 1
                return true
            }
            R.id.episode ->{
                binding.viewPager.currentItem = 2
                return true
            }
            R.id.favorite ->{
                binding.viewPager.currentItem = 3
                return true
            }
        }
        return false
    }

}