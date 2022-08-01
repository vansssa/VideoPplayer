package com.example.videoplayer.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.example.videoplayer.databinding.ItemVideoBinding
import com.example.videoplayer.repository.VideoItems

class NewsItemViewHolder(private val binding: ItemVideoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun updateNewsList(viewModel: MainViewModel, videoItem: VideoItems, callback: PlayerStateCallback) {
        binding.mainViewModel = viewModel
        binding.videoItem = videoItem
        binding.callback = callback
        binding.executePendingBindings()
    }

}
