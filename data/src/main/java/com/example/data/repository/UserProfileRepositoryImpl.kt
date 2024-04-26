package com.example.data.repository

import com.example.data.cloud.UserProfileCloudDataSource
import com.example.data.mappers.toDomain
import com.example.domain.models.UserProfileDomainModel
import com.example.domain.models.UsersListDomainModel
import com.example.domain.repository.UserProfileRepository
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val userProfileCloudDataSource: UserProfileCloudDataSource,
) : UserProfileRepository {
    override suspend fun getCurrentUserProfile(objectId: String): Result<UserProfileDomainModel> {
        return userProfileCloudDataSource.getCurrentUserProfile(objectId).map { it.toDomain() }
    }

    override suspend fun getAllUsers(): Result<UsersListDomainModel> {
        val mappedUsers = userProfileCloudDataSource.getAllUsers()
        return mappedUsers.map { it.toDomain() }
    }
}