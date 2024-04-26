package com.example.domain.usecase

import com.example.domain.models.UserAnswerDomainModel
import com.example.domain.models.UserSignDomainModel

interface UserRegisterUseCase {
    suspend operator fun invoke(
        userSignDomainModel: UserSignDomainModel,
    ): Result<UserAnswerDomainModel>
}