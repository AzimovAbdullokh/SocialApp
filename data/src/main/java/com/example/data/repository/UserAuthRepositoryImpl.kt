package com.example.data.repository

import com.example.data.cloud.UserAuthCloudDataSource
import com.example.data.mappers.toData
import com.example.data.mappers.toDomain
import com.example.data.models.UserProfileResponseModel
import com.example.domain.models.UserProfileDomainModel
import com.example.domain.models.UserSignDomainModel
import com.example.domain.repository.UserAuthRepository
import javax.inject.Inject

class UserAuthRepositoryImpl @Inject constructor(
    private val cloudDataSource: UserAuthCloudDataSource,
) : UserAuthRepository {

    override suspend fun register(
        userSignModel: UserSignDomainModel,
    ): Result<UserProfileDomainModel> {
        val response = cloudDataSource.register(userSignModel.toData())
        return response.map { value: UserProfileResponseModel -> value.toDomain() }
    }

    override suspend fun login(
        username: String,
        userPassword: String,
    ): Result<UserProfileDomainModel> {
        val response = cloudDataSource.login(userName = username, userPassword = userPassword)
        return response.map {  value: UserProfileResponseModel -> value.toDomain() }
    }
}