package com.example.socialapp.presentation.graph

import kotlinx.coroutines.flow.SharedFlow

interface NavigationManager {
    fun navigateTo(route: String, isBackStackClear: Boolean = false)
    fun observeDestinationsFlow(): SharedFlow<Pair<String, Boolean>>
}