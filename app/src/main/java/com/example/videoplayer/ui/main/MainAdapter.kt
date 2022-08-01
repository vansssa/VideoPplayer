package com.example.videoplayer.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.videoplayer.databinding.ItemVideoBinding
import com.example.videoplayer.repository.VideoItems
import com.google.android.exoplayer2.Player

class MainAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<NewsItemViewHolder>(), PlayerStateCallback{

    private lateinit var newsList: List<VideoItems>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemVideoBinding.inflate(layoutInflater, parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val item = newsList[position]
        holder.updateNewsList(viewModel, item, this)
    }

    override fun getItemCount(): Int {
        newsList = viewModel.newsListLiveData.value ?: listOf()
        return newsList.size
    }

    override fun onVideoDurationRetrieved(duration: Long, player: Player) {

    }

    override fun onVideoBuffering(player: Player) {

    }

    override fun onStartedPlaying(player: Player) {

    }

    override fun onFinishedPlaying(player: Player) {

    }

}