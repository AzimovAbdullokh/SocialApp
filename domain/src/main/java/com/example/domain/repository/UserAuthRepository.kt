package com.example.domain.repository

import com.example.domain.models.UserAnswerDomainModel
import com.example.domain.models.UserSignDomainModel

interface UserAuthRepository {

    suspend fun register(
        userSignModel: UserSignDomainModel,
    ): Result<UserAnswerDomainModel>

    suspend fun login(
        username: String,
        userPassword: String,
    ): Result<UserAnswerDomainModel>
}