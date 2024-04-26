package com.example.socialapp.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserSharedPref
import com.example.socialapp.presentation.graph.NavigationManager
import com.example.socialapp.presentation.screens.main.MAIN_GRAPH_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialMainAppViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val userSharedPref: UserSharedPref,
) : ViewModel() {
    val userDestinationFlow = navigationManager.observeDestinationsFlow()
    val isUserSigned = userSharedPref.observeIsUserSigned()

    fun onNavigateToMainGraph() { navigationManager.navigateTo(MAIN_GRAPH_TAG, true) }
}