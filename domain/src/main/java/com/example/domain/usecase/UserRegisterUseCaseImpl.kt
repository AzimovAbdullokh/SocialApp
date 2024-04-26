package com.example.domain.usecase

import com.example.domain.models.UserProfileDomainModel
import com.example.domain.models.UserSignDomainModel
import com.example.domain.repository.UserAuthRepository

class UserRegisterUseCaseImpl(
    private val userAuthRepository: UserAuthRepository,
) : UserRegisterUseCase {

    override suspend fun invoke(
        userSignDomainModel: UserSignDomainModel,
    ): Result<UserProfileDomainModel> = userAuthRepository.register(userSignDomainModel)
}