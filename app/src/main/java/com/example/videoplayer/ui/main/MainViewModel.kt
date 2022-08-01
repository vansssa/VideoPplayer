package com.example.videoplayer.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.api.URLs.IMGURL
import com.example.videoplayer.api.URLs.VIDEOURL
import com.example.videoplayer.repository.VideoItems
import com.example.videoplayer.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository,
                                        application: Application): AndroidViewModel(application) {

    var newsListLiveData : MutableLiveData<List<VideoItems>> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    init {
        getVideoList()
    }


    private fun getVideoList() {
        val resultList = mutableListOf<VideoItems>()
        viewModelScope.launch(Dispatchers.IO) {
            repository.getVideoLists()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    if (it.isSuccessful) {
                        it.body()?.let { entity ->
                            entity.videos.forEach { videos ->
                                resultList.add(
                                    VideoItems(
                                        videos.description,
                                        VIDEOURL + videos.id + ".mp4",
                                        IMGURL + (videos.source[2] as? List<*>)?.get(0)
                                            .toString() + ".jpg"
                                    )
                                )
                            }
                        }
                    }
                }
                .subscribe { result ->
                    newsListLiveData.postValue(resultList)
                }
        }
    }
}