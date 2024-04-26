package com.example.data.cloud

import com.example.data.base.BaseDataSource
import com.example.data.models.UserProfileResponseModel
import com.example.data.models.UserResponseModel
import com.example.data.service.UserService
import javax.inject.Inject

class UserAuthCloudDataSourceImpl @Inject constructor(
    private val userService: UserService,
) : UserAuthCloudDataSource, BaseDataSource() {

    override suspend fun register(
        userResponseModel: UserResponseModel,
    ): Result<UserProfileResponseModel> = invokeResponseRequest {
        userService.registerNewUser(userResponseModel)
    }

    override suspend fun login(
        userName: String,
        userPassword: String,
    ): Result<UserProfileResponseModel> = invokeResponseRequest {
        userService.loginUser(userName, userPassword)
    }
}