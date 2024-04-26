package com.example.socialapp.di

import com.example.data.local.UserSharedPref
import com.example.data.local.UserSharedPrefImpl
import com.example.data.repository.UserAuthRepositoryImpl
import com.example.data.repository.UserProfileRepositoryImpl
import com.example.domain.repository.UserAuthRepository
import com.example.domain.repository.UserProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUserAuthRepository(
        impl: UserAuthRepositoryImpl,
    ): UserAuthRepository

    @Binds
    fun bindUserSharedPref(
        impl: UserSharedPrefImpl,
    ): UserSharedPref

    @Binds
    fun bindUserProfileRepository(
        impl: UserProfileRepositoryImpl,
    ): UserProfileRepository
}