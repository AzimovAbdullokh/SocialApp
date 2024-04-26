package com.example.data.cloud

import com.example.data.base.BaseDataSourceHandler
import com.example.data.models.UserAnswerResponseModel
import com.example.data.models.UserResponseModel
import com.example.data.service.UserService
import javax.inject.Inject

class UserAuthCloudDataSourceImpl @Inject constructor(
    private val userService: UserService,
) : UserAuthCloudDataSource, BaseDataSourceHandler() {

    override suspend fun register(
        userResponseModel: UserResponseModel,
    ): Result<UserAnswerResponseModel> = invokeResponseRequest {
        userService.registerNewUser(userResponseModel)
    }

    override suspend fun login(
        userName: String,
        userPassword: String,
    ): Result<UserAnswerResponseModel> = invokeResponseRequest {
        userService.loginUser(userName, userPassword)
    }
}