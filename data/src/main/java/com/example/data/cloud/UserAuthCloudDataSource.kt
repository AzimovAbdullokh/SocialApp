package com.example.data.cloud

import com.example.data.models.UserProfileResponseModel
import com.example.data.models.UserResponseModel

interface UserAuthCloudDataSource {

    suspend fun register(
        userResponseModel: UserResponseModel,
    ): Result<UserProfileResponseModel>

    suspend fun login(
        userName: String,
        userPassword: String,
    ): Result<UserProfileResponseModel>
}