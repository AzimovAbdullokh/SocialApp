package com.example.domain.usecase

import com.example.domain.models.UserAnswerDomainModel
import com.example.domain.repository.UserAuthRepository

class UserLoginUseCaseImpl(
    private val userAuthRepository: UserAuthRepository,
) : UserLoginUseCase {

    override suspend fun invoke(
        userName: String,
        userPassword: String,
    ): Result<UserAnswerDomainModel> {
        return userAuthRepository.login(userName, userPassword)
    }
}