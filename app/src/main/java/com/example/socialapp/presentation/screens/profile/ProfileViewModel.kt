package com.example.socialapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserSharedPref
import com.example.socialapp.presentation.auth.graph.AUTH_GRAPH_TAG
import com.example.socialapp.presentation.graph.NavigationManager
import com.example.socialapp.presentation.mappers.toUio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userSharedPref: UserSharedPref,
    private val mainNavigationManager: NavigationManager
) : ViewModel() {

    private val _uiState: MutableStateFlow<ProfileUiState> =
        MutableStateFlow(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onEvent(interaction: ProfileInteraction) {
        when (interaction) {
            ProfileInteraction.OnLogoutFromAccount -> doLogoutFromAccount()
        }
    }

    private fun doLogoutFromAccount() {
        userSharedPref.clearAll()
        mainNavigationManager.navigateTo(AUTH_GRAPH_TAG, isBackStackClear = true)
    }

    init {
        viewModelScope.launch {
            val currentUser = userSharedPref.getCurrentUser()
            _uiState.tryEmit(ProfileUiState.Loaded(currentUser.toUio()))
        }
    }
}