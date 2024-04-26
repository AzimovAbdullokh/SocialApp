package com.example.socialapp.presentation.models

import androidx.compose.runtime.Immutable
import com.example.socialapp.presentation.screens.utils.emptyString
import java.io.Serializable

@Immutable
    data class CurrentUserUiModel(
    val objectId: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val userBio: String,
    val userLocation: String,
    val userAvatar: UserAvatarUiModel,
) : Serializable {
    companion object {
        val unknown = CurrentUserUiModel(
            userAvatar = UserAvatarUiModel.unknown,
            userLocation = emptyString(),
            userBio = "User example test bio sdjsabjdbjhsabdjhbsajhbdhjasbjhdbjhsabhjdbjsahbd jasbdhjsabjhd jasbdhjsabjhdbasb",
            username = "exampleAccount@gmail.com",
            lastName = "Account",
            firstName = "Example",
            objectId = String(),
        )
    }
}

@Immutable
data class UserAvatarUiModel(
    val name: String,
    val type: String,
    val url: String,
) : Serializable {
    companion object {
        val unknown = UserAvatarUiModel(
            emptyString(),
            emptyString(),
            emptyString(),
        )
    }
}