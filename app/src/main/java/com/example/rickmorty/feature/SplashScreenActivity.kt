package com.example.rickmorty.feature

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.Window
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseActivity
import com.example.rickmorty.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : BaseActivity() {
    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initUI()
    }

    private fun initUI(){
       Handler().postDelayed(object:Runnable{
           override fun run() {
               val intent = Intent(this@SplashScreenActivity,MainActivity::class.java)
               startActivity(intent)
               finish()
           }

       },3000)
    }

}