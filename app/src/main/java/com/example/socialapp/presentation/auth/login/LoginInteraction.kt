package com.example.socialapp.presentation.auth.login

import androidx.compose.runtime.Immutable
import com.example.domain.models.UserProfileDomainModel

sealed interface LoginInteraction {

    @Immutable
    data class OnUserNameChanged(val username: String) : LoginInteraction

    @Immutable
    data class OnUserPasswordChanged(val password: String) : LoginInteraction

    data object OnLoginButtonClickEvent : LoginInteraction

    data class OnNavigateToMainAppGraph(val profile: UserProfileDomainModel) : LoginInteraction
}