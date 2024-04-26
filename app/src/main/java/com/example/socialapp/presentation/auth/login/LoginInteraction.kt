package com.example.socialapp.presentation.auth.login

import androidx.compose.runtime.Immutable

sealed interface LoginInteraction {

    @Immutable
    data class OnUserNameChanged(val username: String) : LoginInteraction

    @Immutable
    data class OnUserPasswordChanged(val password: String) : LoginInteraction

    data object OnLoginButtonClickEvent : LoginInteraction
}