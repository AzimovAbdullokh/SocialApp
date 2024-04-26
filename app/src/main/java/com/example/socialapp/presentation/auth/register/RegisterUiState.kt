package com.example.socialapp.presentation.auth.register

import androidx.compose.runtime.Immutable

@Immutable
sealed interface RegisterUiState {
    data class Error(val throwable: Throwable) : RegisterUiState

    data object Initial : RegisterUiState

    data object Loading : RegisterUiState
}