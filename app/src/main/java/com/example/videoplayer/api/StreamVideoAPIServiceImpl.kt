package com.example.videoplayer.api

import com.example.videoplayer.api.model.VideoEntity
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class StreamVideoAPIServiceImpl @Inject constructor(private val api: VideoAPI) : StreamVideoAPIService{
    override fun getVideoList(): Observable<Response<VideoEntity?>> {
        return api.getVideoList("0123456789#0#examId")
    }

}