package com.example.rickmorty.app

import android.app.Application
import com.example.rickmorty.app.data.utils.ConnectivityInterceptor
import com.example.rickmorty.app.data.utils.WifiService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){

    companion object{
        lateinit var app : App
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        //setUpServices()
    }

//    private fun setUpServices(){
//        WifiService.instance.initializeWithApplicationContext(this)
//    }
}