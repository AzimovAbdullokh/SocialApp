package com.example.socialapp.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserSharedPref
import com.example.domain.models.UserProfileDomainModel
import com.example.domain.models.UserSignDomainModel
import com.example.domain.usecase.GetCurrentUserProfileUseCase
import com.example.domain.usecase.UserRegisterUseCase
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnNavigateToLoginScreen
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnNavigateUp
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnRegisterButtonClick
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnUserEmailChanged
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnUserFirstNameChanged
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnUserLastNameChanged
import com.example.socialapp.presentation.auth.register.RegisterInteraction.OnUserPasswordChanged
import com.example.socialapp.presentation.extentions.createMutableSharedFlowAsSingleLiveEvent
import com.example.socialapp.presentation.graph.MAIN_GRAPH_TAG
import com.example.socialapp.presentation.graph.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRegisterUseCase: UserRegisterUseCase,
    private val navigationManager: NavigationManager,
    private val userSharedPref: UserSharedPref,
    private val getUserProUseCase: GetCurrentUserProfileUseCase,
) : ViewModel() {

    private val _userInfoState: MutableStateFlow<RegisterUserInfoState> =
        MutableStateFlow(RegisterUserInfoState())
    val userInfoState: StateFlow<RegisterUserInfoState> = _userInfoState.asStateFlow()

    private val _uiState: MutableStateFlow<RegisterUiState> =
        MutableStateFlow(RegisterUiState.Initial)
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _navigateToLoginFlow: MutableStateFlow<Unit> = MutableStateFlow(Unit)
    val navigateToLoginFlow: StateFlow<Unit> = _navigateToLoginFlow

    private val _navControllerFlow = createMutableSharedFlowAsSingleLiveEvent<String>()
    val navControllerFlow: SharedFlow<String> = _navControllerFlow.asSharedFlow()

    fun onInteraction(interaction: RegisterInteraction) {
        when (interaction) {
            is OnNavigateUp -> _navControllerFlow.tryEmit(MAIN_GRAPH_TAG)
            is OnUserEmailChanged -> onUpdateUserEmail(interaction)
            is OnUserFirstNameChanged -> onUpdateFirstName(interaction)
            is OnUserLastNameChanged -> onUpdateLastname(interaction)
            is OnUserPasswordChanged -> onUpdateUserPassword(interaction)
            is OnRegisterButtonClick -> doClickOnRegisterButton()
            is OnNavigateToLoginScreen -> _navigateToLoginFlow.tryEmit(Unit)
        }
    }

    private fun doNavigateToMainAppGraph(profile: UserProfileDomainModel) {
        viewModelScope.launch {
            userSharedPref.saveCurrentUser(profile)
            userSharedPref.saveIsUserSigned()
            navigationManager.navigateTo(MAIN_GRAPH_TAG, isBackStackClear = true)
        }
    }

    private fun doClickOnRegisterButton() {
        if (!validateUserInfoTexts(userInfoState.value)) return
        _uiState.tryEmit(RegisterUiState.Loading)
        viewModelScope.launch {
            setUpUserRequestResponses()
        }
    }

    private suspend fun setUpUserRequestResponses() {
        val userInfosForRequest = UserSignDomainModel(
            userName = userInfoState.value.userEmail,
            userPassword = userInfoState.value.userPassword,
            userFirstName = userInfoState.value.userFirstName,
            userLastname = userInfoState.value.userLastName,
            userEmail = userInfoState.value.userEmail
        )
        coroutineScope {
            val registerRequestDeferred = async {
                userRegisterUseCase(userInfosForRequest)
            }

            val registerRequestValue = registerRequestDeferred.await()

            val profileRequestDeferred = async {
                getUserProUseCase(registerRequestValue.getOrNull()?.objectId.orEmpty())
            }

            val profileRequestValue = profileRequestDeferred.await()

            doNavigateToMainAppGraph(
                profileRequestValue.getOrNull() ?: UserProfileDomainModel.UNKNOWN
            )
        }
    }

    private fun onUpdateFirstName(interaction: OnUserFirstNameChanged) {
        _userInfoState.update { newState ->
            newState.copy(
                userFirstName = interaction.userFirstName
            )
        }
    }

    private fun onUpdateLastname(interaction: OnUserLastNameChanged) {
        _userInfoState.update { newState ->
            newState.copy(
                userLastName = interaction.userLastName

            )
        }
    }

    private fun onUpdateUserEmail(interaction: OnUserEmailChanged) {
        _userInfoState.update { newState ->
            newState.copy(
                userEmail = interaction.userEmail
            )
        }
    }

    private fun onUpdateUserPassword(interaction: OnUserPasswordChanged) {
        _userInfoState.update { newState ->
            newState.copy(
                userPassword = interaction.userPassword
            )
        }
    }

    private fun validateUserInfoTexts(
        userInfoTexts: RegisterUserInfoState,
    ): Boolean {
        with(userInfoTexts) {
            val listOfUserInfos = listOf(
                userFirstName,
                userLastName,
                userEmail,
                userPassword
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