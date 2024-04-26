package com.example.socialapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserSharedPref
import com.example.socialapp.presentation.auth.graph.AUTH_GRAPH_TAG
import com.example.socialapp.presentation.graph.MAIN_GRAPH_TAG
import com.example.socialapp.presentation.graph.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SPLASH_SCREEN_MILLIS = 1_800L

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userSharedPref: UserSharedPref,
    private val navigationManager: NavigationManager,
) : ViewModel() {

    init {
        viewModelScope.launch {
            delay(SPLASH_SCREEN_MILLIS)
            if (userSharedPref.observeIsUserSigned()) {
                navigationManager.navigateTo(MAIN_GRAPH_TAG, true)
            } else {
                navigationManager.navigateTo(AUTH_GRAPH_TAG, true)
            }
        }
    }
}