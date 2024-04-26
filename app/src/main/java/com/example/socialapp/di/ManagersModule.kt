package com.example.socialapp.di

import com.example.socialapp.presentation.graph.NavigationManager
import com.example.socialapp.presentation.graph.NavigationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ManagersModule {

    @Binds
    fun bindNavigationManager(
        implementation: NavigationManagerImpl,
    ): NavigationManager
}