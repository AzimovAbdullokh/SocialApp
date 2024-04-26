package com.example.domain.usecase

import com.example.domain.models.UserProfileDomainModel
import com.example.domain.repository.UserProfileRepository

class GetCurrentUserProfileUseCaseImpl(
    private val userProfileRepository: UserProfileRepository,
) : GetCurrentUserProfileUseCase {

    override suspend fun invoke(objectId: String): Result<UserProfileDomainModel> {
        return userProfileRepository.getCurrentUserProfile(objectId)
    }
}