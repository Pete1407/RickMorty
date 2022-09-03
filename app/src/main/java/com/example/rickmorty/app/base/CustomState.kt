package com.example.rickmorty.app.base

interface CustomState {

    fun initUI()
    fun initListener()
    fun initViewModel()
    fun showLoading()
    fun hideLoading()
}