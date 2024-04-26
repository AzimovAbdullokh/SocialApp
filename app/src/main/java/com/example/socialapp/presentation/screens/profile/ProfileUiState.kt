package com.example.socialapp.presentation.screens.profile

import androidx.compose.runtime.Immutable
import com.example.socialapp.presentation.models.CurrentUserUiModel

@Immutable
sealed interface ProfileUiState {

    data object Loading : ProfileUiState

    data class Loaded(val profile: CurrentUserUiModel) : ProfileUiState

    data class Error(val throwable: Throwable) : ProfileUiState
}