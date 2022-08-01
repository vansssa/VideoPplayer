package com.example.videoplayer.repository

import com.example.videoplayer.api.model.VideoEntity
import io.reactivex.Observable
import retrofit2.Response

interface Repository {
    suspend fun getVideoLists(): Observable<Response<VideoEntity?>>
}