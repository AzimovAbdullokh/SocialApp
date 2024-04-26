package com.example.socialapp.presentation.auth.register

import androidx.compose.runtime.Immutable

sealed interface RegisterInteraction {

    @Immutable
    data class OnUserFirstNameChanged(val userFirstName: String) : RegisterInteraction

    @Immutable
    data class OnUserLastNameChanged(val userLastName: String) : RegisterInteraction

    @Immutable
    data class OnUserEmailChanged(val userEmail: String) : RegisterInteraction

    @Immutable
    data class OnUserPasswordChanged(val userPassword: String) : RegisterInteraction

    data object OnNavigateUp : RegisterInteraction

    data object OnRegisterButtonClick : RegisterInteraction

    data object OnNavigateToLoginScreen : RegisterInteraction
}