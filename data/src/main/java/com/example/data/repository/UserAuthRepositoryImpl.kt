package com.example.data.repository

import com.example.data.cloud.UserAuthCloudDataSource
import com.example.data.mappers.toData
import com.example.data.mappers.toDomain
import com.example.data.models.UserAnswerResponseModel
import com.example.domain.models.UserAnswerDomainModel
import com.example.domain.models.UserSignDomainModel
import com.example.domain.repository.UserAuthRepository
import javax.inject.Inject

class UserAuthRepositoryImpl @Inject constructor(
    private val cloudDataSource: UserAuthCloudDataSource,
) : UserAuthRepository {

    override suspend fun register(
        userSignModel: UserSignDomainModel,
    ): Result<UserAnswerDomainModel> {
        val response = cloudDataSource.register(userSignModel.toData())
        return response.map { value: UserAnswerResponseModel -> value.toDomain() }
    }

    override suspend fun login(
        username: String,
        userPassword: String,
    ): Result<UserAnswerDomainModel> {
        val response = cloudDataSource.login(userName = username, userPassword = userPassword)
        return response.map {  value: UserAnswerResponseModel -> value.toDomain() }
    }
}