package com.example.socialapp.presentation.graph

import com.example.socialapp.presentation.extentions.createMutableSharedFlowAsSingleLiveEvent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManagerImpl @Inject constructor() : NavigationManager {

    private val navigationDestinationFlow =
        createMutableSharedFlowAsSingleLiveEvent<Pair<String, Boolean>>()

    override fun navigateTo(route: String, isBackStackClear: Boolean) {
        navigationDestinationFlow.tryEmit(route to isBackStackClear)
    }

    override fun observeDestinationsFlow(): SharedFlow<Pair<String, Boolean>> {
        return navigationDestinationFlow.asSharedFlow()
    }
}