package com.example.socialapp.presentation.models

import androidx.compose.runtime.Stable

@Stable
@JvmInline
value class UsersListUiModel(
    val result: List<CurrentUserUiModel>,
) {
    companion object {
        fun usersPreview() = listOf(
            CurrentUserUiModel.unknown.copy(objectId = "alkndsals"),
            CurrentUserUiModel.unknown.copy(objectId = "alkndseasdls"),
            CurrentUserUiModel.unknown.copy(objectId = "alkndsafesdfls"),
            CurrentUserUiModel.unknown.copy(objectId = "alkndsasdfffls"),
        )
    }
}