package com.example.rickmorty.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.rickmorty.base.BaseFragment

class PagerAdapter(manager : FragmentManager) : FragmentStatePagerAdapter(manager) {
    private var list = ArrayList<BaseFragment>()

    fun setPageItems(pages : ArrayList<BaseFragment>){
        list = pages
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

}