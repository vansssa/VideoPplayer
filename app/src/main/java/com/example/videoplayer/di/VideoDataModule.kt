package com.example.videoplayer.di

import com.example.videoplayer.repository.Repository
import com.example.videoplayer.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class VideoDataModule {

    @Singleton
    @Binds
    abstract fun bindDatabaseLogger(impl: RepositoryImpl): Repository

}