package com.example.videoplayer.ui.main

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(context)
            .load(url)
            .circleCrop()
            .into(this);
    }
}

@BindingAdapter(value = ["video_url", "on_state_change"], requireAll = false)
fun PlayerView.loadVideo(url: String?, callback: PlayerStateCallback) {
    if (url == null) return
    val player = SimpleExoPlayer.Builder(context).build()

    player.playWhenReady = true
    player.repeatMode = Player.REPEAT_MODE_ALL
    // When changing track, retain the latest frame instead of showing a black screen
    setKeepContentOnPlayerReset(true)
    // We'll show the controller, change to true if want controllers as pause and start
    //this.useController = false
    // Provide url to load the video from here
    val mediaSource = MediaItem.fromUri(url)
    this.controllerAutoShow = false

    // Prepare the player with the source.
    player.setMediaItem(mediaSource)
    player.seekTo(0, 0)
    player.prepare()

    this.player = player


    this.player!!.addListener(object : Player.EventListener {

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            super.onPlayerStateChanged(playWhenReady, playbackState)

            if (playbackState == Player.STATE_BUFFERING) callback.onVideoBuffering(player) // Buffering.. set progress bar visible here
            if (playbackState == Player.STATE_READY){
                // [PlayerView] has fetched the video duration so this is the block to hide the buffering progress bar
                callback.onVideoDurationRetrieved((this@loadVideo.player as SimpleExoPlayer).duration, player)
            }
            if (playbackState == Player.STATE_READY && player.playWhenReady){
                // [PlayerView] has started playing/resumed the video
                callback.onStartedPlaying(player)
            }
        }
    })

}
