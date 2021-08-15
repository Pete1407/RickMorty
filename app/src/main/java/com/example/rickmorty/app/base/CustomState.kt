package com.example.rickmorty.app.base

interface CustomState {

    fun initUI()
    fun initViewModel()
    fun showLoading()
    fun hideLoading()
}