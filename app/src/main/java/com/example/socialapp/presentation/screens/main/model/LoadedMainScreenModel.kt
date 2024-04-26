package com.example.socialapp.presentation.screens.main.model

import androidx.compose.runtime.Immutable
import com.example.socialapp.presentation.models.UsersListUiModel

@Immutable
data class LoadedMainScreenModel(
    val userName: String,
    val allUsers: UsersListUiModel,
) {
    companion object {
        val Preview = LoadedMainScreenModel(
            userName = "Abdullokh",
            allUsers = UsersListUiModel(
                result = UsersListUiModel.usersPreview()
            )
        )
    }
}