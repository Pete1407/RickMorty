package com.example.rickmorty.feature

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseActivity
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.databinding.ActivityMainBinding
import com.example.rickmorty.feature.character.CharacterFragment
import com.example.rickmorty.feature.episode.EpisodeFragment
import com.example.rickmorty.feature.favorite_character.FavoriteFragment
import com.example.rickmorty.feature.location.LocationFragment
import com.example.rickmorty.app.data.utils.adapter.PagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private var view: View? = null
    private var list = ArrayList<BaseFragment>()
    private lateinit var pager: PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)
        loadFragment(CharacterFragment())
        initUI()
    }

    private fun initUI() {
        setMenuBottomNavigation()
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.character -> {
                    loadFragment(CharacterFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.location -> {
                    loadFragment(LocationFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.episode -> {
                    loadFragment(EpisodeFragment())
                    return@setOnItemSelectedListener true
                }
                else -> {
                    loadFragment(FavoriteFragment())
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    private fun setMenuBottomNavigation() {
        pager = PagerAdapter(supportFragmentManager)
        list.add(CharacterFragment.newInstance())
        list.add(LocationFragment.newInstance())
        list.add(EpisodeFragment.newInstance())
        list.add(FavoriteFragment.newInstance())
        pager.setPageItems(list)
    }

}