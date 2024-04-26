package com.example.socialapp.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserSharedPref
import com.example.domain.models.UserSignDomainModel
import com.example.domain.usecase.UserRegisterUseCase
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnNavigateUp
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnRegisterButtonClick
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnUserEmailChanged
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnUserFirstNameChanged
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnUserLastNameChanged
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnUserPasswordChanged
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
class RegisterViewModel @Inject constructor(
    private val userRegisterUseCase: UserRegisterUseCase,
    private val navigationManager: NavigationManager,
    private val userSharedPref: UserSharedPref,
) : ViewModel() {

    private val _uiState: MutableStateFlow<RegisterUiState> = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _navigateUpFlow: MutableStateFlow<Unit> = MutableStateFlow(Unit)
    val navigateUpFlow: StateFlow<Unit> = _navigateUpFlow.asStateFlow()

    private val _navigateToLoginFlow: MutableStateFlow<Unit> = MutableStateFlow(Unit)
    val navigateToLoginFlow: StateFlow<Unit> = _navigateToLoginFlow

    fun onInteraction(interaction: RegisterInteraction) {
        when (interaction) {
            is OnNavigateUp -> _navigateUpFlow.tryEmit(Unit)
            is OnUserEmailChanged -> onUpdateUserEmail(interaction)
            is OnUserFirstNameChanged -> onUpdateFirstName(interaction)
            is OnUserLastNameChanged -> onUpdateLastname(interaction)
            is OnUserPasswordChanged -> onUpdateUserPassword(interaction)
            is OnRegisterButtonClick -> doClickOnRegisterButton()
            is RegisterInteraction.OnNavigateToLoginScreen -> _navigateToLoginFlow.tryEmit(Unit)
        }
    }

    private fun doClickOnRegisterButton(): Unit = with(uiState.value) {
        if (!validateUserInfoTexts(listOf(userFirstName, userLastName, userEmail, userPassword))) {
            return
        }
        viewModelScope.launch {
            val request = userRegisterUseCase(
                UserSignDomainModel(
                    userName = userEmail,
                    userPassword = userPassword,
                    userFirstName = userFirstName,
                    userLastname = userLastName,
                    userEmail = userEmail
                )
            )
            if (request.isSuccess) {
                navigationManager.navigateTo(MAIN_GRAPH_TAG, isBackStackClear = true)
                userSharedPref.saveIsUserSigned()
            }
        }
    }

    private fun onUpdateFirstName(interaction: OnUserFirstNameChanged) {
        _uiState.update { newState ->
            newState.copy(
                userFirstName = interaction.userFirstName
            )
        }
    }

    private fun onUpdateLastname(interaction: OnUserLastNameChanged) {
        _uiState.update { newState ->
            newState.copy(
                userLastName = interaction.userLastName

            )
        }
    }

    private fun onUpdateUserEmail(interaction: OnUserEmailChanged) {
        _uiState.update { newState ->
            newState.copy(
                userEmail = interaction.userEmail
            )
        }
    }

    private fun onUpdateUserPassword(interaction: OnUserPasswordChanged) {
        _uiState.update { newState ->
            newState.copy(
                userPassword = interaction.userPassword
            )
        }
    }

    private fun validateUserInfoTexts(texts: List<String>): Boolean {
        var isValidateTexts = false
        texts.forEach {
            if (it.isNotEmpty()) {
                isValidateTexts = true
            }
        }
        return isValidateTexts
    }
}