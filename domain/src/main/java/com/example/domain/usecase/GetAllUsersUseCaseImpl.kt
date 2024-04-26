package com.example.domain.usecase

import com.example.domain.models.UsersListDomainModel
import com.example.domain.repository.UserProfileRepository

class GetAllUsersUseCaseImpl(
    private val profileRepository: UserProfileRepository,
) : GetAllUsersUseCase {
    override suspend fun invoke(): Result<UsersListDomainModel> {
        return profileRepository.getAllUsers()
    }
}