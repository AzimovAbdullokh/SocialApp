package com.example.data.cloud

import com.example.data.models.ListProfileModelResponse
import com.example.data.models.UserProfileResponseModel

interface UserProfileCloudDataSource {
    suspend fun getCurrentUserProfile(
        objectId: String,
    ): Result<UserProfileResponseModel>

    suspend fun getAllUsers(): Result<ListProfileModelResponse>
}