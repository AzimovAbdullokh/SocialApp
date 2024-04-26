package com.example.data.cloud

import com.example.data.models.UserAnswerResponseModel
import com.example.data.models.UserResponseModel

interface UserAuthCloudDataSource {

    suspend fun register(
        userResponseModel: UserResponseModel,
    ): Result<UserAnswerResponseModel>

    suspend fun login(
        userName: String,
        userPassword: String,
    ): Result<UserAnswerResponseModel>
}