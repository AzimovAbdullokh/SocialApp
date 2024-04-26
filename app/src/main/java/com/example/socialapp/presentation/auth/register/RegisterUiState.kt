package com.example.socialapp.presentation.auth.register

import androidx.compose.runtime.Immutable
import com.example.socialapp.presentation.screens.utils.emptyString

@Immutable
data class RegisterUiState(
    val userFirstName: String = emptyString(),
    val userLastName: String = emptyString(),
    val userEmail: String = emptyString(),
    val userPassword: String = emptyString(),
)