package com.example.data.models

data class UserAnswerResponseModel(
    val objectId: String,
    val userAvatar: String,
) {
    companion object {
        val unknown = UserAnswerResponseModel(
            objectId = String(),
            userAvatar = String(),
        )
    }
}