package com.example.rickmorty.feature

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.Window
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseActivity
import com.example.rickmorty.databinding.ActivityMainBinding
import com.example.rickmorty.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : BaseActivity() {
    private val binding : ActivitySplashScreenBinding by lazy {
        ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI(){
       Handler(Looper.getMainLooper()).postDelayed(object:Runnable{
           override fun run() {
               val intent = Intent(this@SplashScreenActivity,MainActivity::class.java)
               startActivity(intent)
               finish()
           }

       },3000)
    }

}