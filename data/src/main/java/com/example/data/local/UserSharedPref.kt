package com.example.data.local

import com.example.domain.models.UserProfileDomainModel

interface UserSharedPref {
    suspend fun saveIsUserSigned()
    fun observeIsUserSigned(): Boolean

    fun saveCurrentUser(user: UserProfileDomainModel)
    fun getCurrentUser(): UserProfileDomainModel

    fun clearAll()
}