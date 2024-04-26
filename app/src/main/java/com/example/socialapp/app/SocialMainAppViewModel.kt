package com.example.socialapp.app

import androidx.lifecycle.ViewModel
import com.example.socialapp.presentation.graph.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SocialMainAppViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {
    val userDestinationFlow = navigationManager.observeDestinationsFlow()
}