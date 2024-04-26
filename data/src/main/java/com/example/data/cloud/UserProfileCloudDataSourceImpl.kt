package com.example.data.cloud

import com.example.data.base.BaseDataSource
import com.example.data.models.ListProfileModelResponse
import com.example.data.models.UserProfileResponseModel
import com.example.data.service.UserService
import javax.inject.Inject

class UserProfileCloudDataSourceImpl @Inject constructor(
    private val userService: UserService,
) : UserProfileCloudDataSource, BaseDataSource() {

    override suspend fun getCurrentUserProfile(objectId: String): Result<UserProfileResponseModel> {
        val userId = "{\"objectId\":\"$objectId\"}"
        val response = userService.getUserProfile(userId)
        return if (response.isSuccessful) {
            Result.success(
                response.body()?.results?.firstOrNull() ?: UserProfileResponseModel.UNKNOWN
            )
        } else {
            Result.failure(Throwable(response.message()))
        }
    }

    override suspend fun getAllUsers(): Result<ListProfileModelResponse> {
        return invokeResponseRequest { userService.getAllUsers() }
    }
}