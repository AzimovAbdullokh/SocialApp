package com.example.socialapp.di

import com.example.data.cloud.UserAuthCloudDataSource
import com.example.data.cloud.UserAuthCloudDataSourceImpl
import com.example.data.cloud.UserProfileCloudDataSource
import com.example.data.cloud.UserProfileCloudDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CloudDataSourceModule {

    @Binds
    fun bindUserAuthCloudDataSource(
        implementation: UserAuthCloudDataSourceImpl,
    ): UserAuthCloudDataSource

    @Binds
    fun bindUserProfileCloudDataSource(
        implementation: UserProfileCloudDataSourceImpl,
    ): UserProfileCloudDataSource
}