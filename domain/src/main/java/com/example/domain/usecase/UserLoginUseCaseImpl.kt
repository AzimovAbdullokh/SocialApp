package com.example.domain.usecase

import com.example.domain.models.UserProfileDomainModel
import com.example.domain.repository.UserAuthRepository

class UserLoginUseCaseImpl(
    private val userAuthRepository: UserAuthRepository,
) : UserLoginUseCase {

    override suspend fun invoke(
        userName: String,
        userPassword: String,
    ): Result<UserProfileDomainModel> {
        return userAuthRepository.login(userName, userPassword)
    }
}