package com.example.rickmorty.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseActivity
import com.example.rickmorty.app.base.BaseFragment
import com.example.rickmorty.databinding.ActivityMainBinding
import com.example.rickmorty.feature.character.CharacterFragment
import com.example.rickmorty.feature.episode.EpisodeFragment
import com.example.rickmorty.feature.favorite_character.FavoriteFragment
import com.example.rickmorty.feature.location.LocationFragment
import com.example.rickmorty.app.data.utils.adapter.PagerAdapter
import com.example.rickmorty.feature.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var view: View? = null
    private var list = ArrayList<BaseFragment>()
    private lateinit var pager: PagerAdapter
    private var fragmentList = ArrayList<BaseFragment>()
    private var characterFragment : CharacterFragment? = null
    private var locationFragment : LocationFragment? = null
    private var episodeFragment : EpisodeFragment? = null
    private var searchFragment : SearchFragment? = null
    private var defaultFragment : BaseFragment? = null
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.frameLayout) as NavHostFragment
        navController = navHostFragment.navController
        characterFragment = CharacterFragment()
        locationFragment = LocationFragment()
        episodeFragment = EpisodeFragment()
        searchFragment = SearchFragment()

        // set default fragment

        defaultFragment = characterFragment
        setDefaultMenu(defaultFragment!!)

        fragmentList.let {
            it.add(searchFragment!!)
            it.add(episodeFragment!!)
            it.add(locationFragment!!)
            it.add(characterFragment!!)
        }
        loadFragment(fragmentList)
        initUI()
    }

    private fun initUI() {
        binding.bottomNav.setOnItemSelectedListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            when(it.itemId){
                R.id.character ->{
                    fragmentTransaction.hide(defaultFragment!!).show(characterFragment!!).commit()
                    defaultFragment = characterFragment
                    return@setOnItemSelectedListener true
                }
                R.id.location ->{
                    fragmentTransaction.hide(defaultFragment!!).show(locationFragment!!).commit()
                    defaultFragment = locationFragment
                    return@setOnItemSelectedListener true
                }
                R.id.episode ->{
                    fragmentTransaction.hide(defaultFragment!!).show(episodeFragment!!).commit()
                    defaultFragment = episodeFragment
                    return@setOnItemSelectedListener true
                }
                else ->{
                    fragmentTransaction.hide(defaultFragment!!).show(searchFragment!!).commit()
                    defaultFragment = searchFragment
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    private fun loadFragment(list: ArrayList<BaseFragment>) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        list.forEach { item ->
            when(item){
                is CharacterFragment ->{
                    fragmentTransaction.add(R.id.frameLayout,item,"tag_character")
                }
                is EpisodeFragment ->{
                    fragmentTransaction.add(R.id.frameLayout,item,"tag_episode").hide(item)
                }
                is LocationFragment ->{
                    fragmentTransaction.add(R.id.frameLayout,item,"tag_location").hide(item)
                }
                else ->{
                    fragmentTransaction.add(R.id.frameLayout,item,"tag_search").hide(item)
                }
            }

        }
        fragmentTransaction.commit()
    }

//    private fun setMenuBottomNavigation() {
//        pager = PagerAdapter(supportFragmentManager)
//        list.add(CharacterFragment.newInstance())
//        list.add(LocationFragment.newInstance())
//        list.add(EpisodeFragment.newInstance())
//        list.add(FavoriteFragment.newInstance())
//        pager.setPageItems(list)
//    }

    private fun setDefaultMenu(fragment : BaseFragment){
        when(fragment){
            is CharacterFragment ->{
                binding.bottomNav.selectedItemId = R.id.character
            }
            is LocationFragment ->{
                binding.bottomNav.selectedItemId = R.id.location
            }
            else ->{
                binding.bottomNav.selectedItemId = R.id.episode
            }
        }
    }

}