package com.example.domain.usecase

import com.example.domain.models.UserProfileDomainModel

interface GetCurrentUserProfileUseCase {
    suspend operator fun invoke(objectId: String): Result<UserProfileDomainModel>
}