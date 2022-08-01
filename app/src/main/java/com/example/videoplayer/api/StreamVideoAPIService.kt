package com.example.videoplayer.api

import com.example.videoplayer.api.model.VideoEntity
import io.reactivex.Observable
import retrofit2.Response

interface StreamVideoAPIService {
    fun getVideoList(): Observable<Response<VideoEntity?>>
}