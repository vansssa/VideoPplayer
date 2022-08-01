package com.example.videoplayer.repository

import com.example.videoplayer.api.StreamVideoAPIService
import com.example.videoplayer.api.model.VideoEntity
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor (val apiService: StreamVideoAPIService) : Repository {


    override suspend fun getVideoLists(): Observable<Response<VideoEntity?>> {
      return apiService.getVideoList()
    }
}