package com.example.domain.models

import java.io.Serializable

data class UsersListDomainModel(
    val result: List<UserProfileDomainModel>,
)

data class UserProfileDomainModel(
    val objectId: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val userBio: String,
    val userLocation: String,
    val userAvatar: UserAvatarDomainModel,
) : Serializable {
    companion object {
        val UNKNOWN = UserProfileDomainModel(
            "",
            "",
            "",
            "",
            "",
            "",
            UserAvatarDomainModel("", "", "")
        )
    }
}

data class UserAvatarDomainModel(
    val name: String,
    val type: String,
    val url: String,
) : Serializable {
    companion object {
        val UNKNOWN = UserAvatarDomainModel(
            name = String(), type = String(), url = String()
        )
    }
}