package com.example.domain.usecase

import com.example.domain.models.UserProfileDomainModel

interface UserLoginUseCase {
    suspend operator fun invoke(
        userName: String,
        userPassword: String,
    ): Result<UserProfileDomainModel>
}