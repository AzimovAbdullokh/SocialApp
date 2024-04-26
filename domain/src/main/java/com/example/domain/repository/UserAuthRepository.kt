package com.example.domain.repository

import com.example.domain.models.UserProfileDomainModel
import com.example.domain.models.UserSignDomainModel

interface UserAuthRepository {

    suspend fun register(
        userSignModel: UserSignDomainModel,
    ): Result<UserProfileDomainModel>

    suspend fun login(
        username: String,
        userPassword: String,
    ): Result<UserProfileDomainModel>
}