package com.example.videoplayer.api.model

import com.google.gson.annotations.SerializedName

data class VideoEntity(
    @field:SerializedName("p") val videos: List<VideoBean>,
) {

    data class VideoBean(
        @field:SerializedName("id") val id: String,
        @field:SerializedName("desc") val description: String,
        @field:SerializedName("source") val source: List<Any>
    )
}
