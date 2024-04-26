package com.example.socialapp.presentation.graph

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManagerImpl @Inject constructor() : NavigationManager {

    private val navigationDestinationFlow = MutableSharedFlow<Pair<String, Boolean>>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    override fun navigateTo(route: String, isBackStackClear: Boolean) {
        navigationDestinationFlow.tryEmit(route to isBackStackClear)
    }

    override fun observeDestinationsFlow(): SharedFlow<Pair<String, Boolean>> {
        return navigationDestinationFlow.asSharedFlow()
    }
}