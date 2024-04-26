package com.example.socialapp.presentation.auth.login

import androidx.compose.runtime.Immutable
import com.example.domain.models.UserProfileDomainModel

@Immutable
sealed interface LoginUiState {
    data class Loaded(val profile: UserProfileDomainModel) : LoginUiState

    data class Error(val throwable: Throwable) : LoginUiState

    data object Initial : LoginUiState

    data object Loading : LoginUiState
}