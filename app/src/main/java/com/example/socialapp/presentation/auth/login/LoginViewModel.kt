package com.example.socialapp.presentation.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserSharedPref
import com.example.domain.models.UserProfileDomainModel
import com.example.domain.usecase.UserLoginUseCase
import com.example.socialapp.presentation.auth.login.LoginInteraction.OnLoginButtonClickEvent
import com.example.socialapp.presentation.auth.login.LoginInteraction.OnNavigateToMainAppGraph
import com.example.socialapp.presentation.auth.login.LoginInteraction.OnUserNameChanged
import com.example.socialapp.presentation.auth.login.LoginInteraction.OnUserPasswordChanged
import com.example.socialapp.presentation.auth.login.LoginUiState.Error
import com.example.socialapp.presentation.auth.login.LoginUiState.Initial
import com.example.socialapp.presentation.auth.login.LoginUiState.Loaded
import com.example.socialapp.presentation.auth.login.LoginUiState.Loading
import com.example.socialapp.presentation.graph.MAIN_GRAPH_TAG
import com.example.socialapp.presentation.graph.NavigationManager
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

    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(Initial)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _userInfoState: MutableStateFlow<LoginUserInfoState> =
        MutableStateFlow(LoginUserInfoState())
    val userInfoState: StateFlow<LoginUserInfoState> = _userInfoState.asStateFlow()

    fun onInteraction(interaction: LoginInteraction) {
        when (interaction) {
            is OnLoginButtonClickEvent -> doUserLoginRequest()
            is OnUserNameChanged -> doUserNameChange(interaction)
            is OnUserPasswordChanged -> doUserPasswordChanged(interaction)
            is OnNavigateToMainAppGraph -> doNavigateToMainAppGraph(interaction)
        }
    }

    private fun doNavigateToMainAppGraph(interaction: OnNavigateToMainAppGraph) {
        viewModelScope.launch {
            navigationManager.navigateTo(MAIN_GRAPH_TAG, isBackStackClear = true)
            userSharedPref.saveCurrentUser(interaction.profile)
            userSharedPref.saveIsUserSigned()
        }
    }

    private fun doUserLoginRequest() {
        if (!validateUserInfoTexts(userInfoState.value)) return
        _uiState.tryEmit(Loading)
        viewModelScope.launch {
            val response = userLoginUseCase(
                userName = _userInfoState.value.userName,
                userPassword = _userInfoState.value.userPassword
            )
            _uiState.tryEmit(
                if (response.isSuccess) Loaded(
                    response.getOrNull() ?: UserProfileDomainModel.UNKNOWN
                )
                else Error(response.exceptionOrNull()?.cause ?: Throwable())
            )
        }
    }

    private fun doUserNameChange(interaction: OnUserNameChanged) {
        _userInfoState.update { newState ->
            newState.copy(
                userName = interaction.username
            )
        }
    }

    private fun doUserPasswordChanged(interaction: OnUserPasswordChanged) {
        _userInfoState.update { newState ->
            newState.copy(
                userPassword = interaction.password
            )
        }
    }

    private fun validateUserInfoTexts(
        userInfoTexts: LoginUserInfoState,
    ): Boolean {
        with(userInfoTexts) {
            val listOfUserInfos = listOf(
                userName, userPassword
            )
            var isValidateTexts = false
            listOfUserInfos.forEach {
                if (it.isNotEmpty()) {
                    isValidateTexts = true
                }
            }
            return isValidateTexts
        }
    }
}