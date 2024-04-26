package com.example.domain.repository

import com.example.domain.models.UserProfileDomainModel
import com.example.domain.models.UsersListDomainModel

interface UserProfileRepository {

    suspend fun getCurrentUserProfile(objectId: String): Result<UserProfileDomainModel>

    suspend fun getAllUsers():Result<UsersListDomainModel>
}