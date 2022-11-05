package com.example.rickmorty.feature.episode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.rickmorty.R
import com.example.rickmorty.app.base.BaseActivity
import com.example.rickmorty.app.base.CustomState
import com.example.rickmorty.app.data.model.Episode
import com.example.rickmorty.databinding.ActivityEpisodeDetailBinding

class EpisodeDetailActivity : BaseActivity(),CustomState {

    private var episode : Episode? = null
    private var seasonText : String = ""

    private val binding : ActivityEpisodeDetailBinding by lazy{
        ActivityEpisodeDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        episode = intent.getParcelableExtra(PARAM_EPISODE)
        seasonText = intent.getStringExtra(PARAM_SEASON)?:""
        initListener()
        initUI()
        initViewModel()
    }

    override fun initUI() {
        binding.episodeName.text = episode?.name
        binding.tagSeason.setValueTag(seasonText)
        binding.tagEpisode.setValueTag(episode?.episodeText?:"")

    }

    override fun initListener() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initViewModel() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    companion object{
        const val PARAM_EPISODE = "param-episode"
        const val PARAM_SEASON = "param-season-text"

        fun startEpisodeDetailActivity(context: Context,episode : Episode,seasonText : String) {
            context.startActivity(Intent(context, EpisodeDetailActivity::class.java).apply {
                putExtra(PARAM_EPISODE,episode)
                putExtra(PARAM_SEASON,seasonText)
            })
        }
    }

}