package com.example.videoplayer.api

import com.example.videoplayer.api.model.VideoEntity
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface VideoAPI {


    @Headers("Accept: application/json")
    @GET("test1.0/backstage/exm1")
    fun getVideoList(@Header("Authorization") authorization: String): Observable<Response<VideoEntity?>>

}