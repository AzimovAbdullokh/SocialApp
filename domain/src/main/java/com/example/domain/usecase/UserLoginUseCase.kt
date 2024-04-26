package com.example.domain.usecase

import com.example.domain.models.UserAnswerDomainModel

interface UserLoginUseCase {
    suspend operator fun invoke(
        userName: String,
        userPassword: String,
    ): Result<UserAnswerDomainModel>
}