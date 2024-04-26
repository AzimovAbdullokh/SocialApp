package com.example.socialapp.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserSharedPref
import com.example.domain.usecase.UserLoginUseCase
import com.example.socialapp.presentation.graph.NavigationManager
import com.example.socialapp.presentation.screens.main.MAIN_GRAPH_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase,
    private val navigationManager: NavigationManager,
    private val userSharedPref: UserSharedPref,
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onInteraction(interaction: LoginInteraction) {
        when (interaction) {
            is LoginInteraction.OnLoginButtonClickEvent -> doUserLoginRequest()
            is LoginInteraction.OnUserNameChanged -> doUserNameChange(interaction)
            is LoginInteraction.OnUserPasswordChanged -> doUserPasswordChanged(interaction)
        }
    }

    private fun doUserLoginRequest() {
        viewModelScope.launch {
            val response = userLoginUseCase(
                userName = _uiState.value.userName,
                userPassword = _uiState.value.userPassword
            )

            if (response.isSuccess) {
                navigationManager.navigateTo(MAIN_GRAPH_TAG, isBackStackClear = true)
                userSharedPref.saveIsUserSigned()
            }
        }
    }

    private fun doUserNameChange(interaction: LoginInteraction.OnUserNameChanged) {
        _uiState.update { newState ->
            newState.copy(
                userName = interaction.username
            )
        }
    }

    private fun doUserPasswordChanged(interaction: LoginInteraction.OnUserPasswordChanged) {
        _uiState.update { newState ->
            newState.copy(
                userPassword = interaction.password
            )
        }
    }
}