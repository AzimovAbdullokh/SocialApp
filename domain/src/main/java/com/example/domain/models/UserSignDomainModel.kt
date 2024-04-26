package com.example.domain.models

data class UserSignDomainModel(
    val userName: String,
    val userPassword: String,
    val userEmail: String,
    val userFirstName: String,
    val userLastname: String,
    val userBio: String = String(),
    val userAvatar: String = String(),
    val userLocation: String = String(),
) {
    companion object {
        val NONE = UserSignDomainModel(
            userName = String(),
            userPassword = String(),
            userEmail = String(),
            userFirstName = String(),
            userLastname = String(),
            userBio = String(),
            userAvatar = String(),
            userLocation = String(),
        )
    }
}
