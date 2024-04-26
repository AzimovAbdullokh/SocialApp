package com.example.socialapp.presentation.screens.main

import androidx.compose.runtime.Immutable
import com.example.socialapp.presentation.screens.main.model.LoadedMainScreenModel

@Immutable
sealed interface MainScreenUiState {

    data object Loading : MainScreenUiState

    @Immutable
    data class Loaded(val loadedMainScreen: LoadedMainScreenModel) : MainScreenUiState

    data class Error(val throwable: Throwable) : MainScreenUiState
}