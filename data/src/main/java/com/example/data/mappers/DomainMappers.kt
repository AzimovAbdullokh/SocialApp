package com.example.data.mappers

import com.example.data.models.ListProfileModelResponse
import com.example.data.models.UserAvatarResponseModel
import com.example.data.models.UserProfileResponseModel
import com.example.domain.models.UserAvatarDomainModel
import com.example.domain.models.UserProfileDomainModel
import com.example.domain.models.UsersListDomainModel

fun UserAvatarResponseModel.toDomain() = UserAvatarDomainModel(
    name = name,
    type = type,
    url = url,
)

fun UserProfileResponseModel.toDomain() = UserProfileDomainModel(
    username = username.orEmpty(),
    firstName = firstName.orEmpty(),
    lastName = lastName.orEmpty(),
    userBio = userBio.orEmpty(),
    userLocation = userLocation.orEmpty(),
    userAvatar = userAvatar?.toDomain() ?: UserAvatarDomainModel.UNKNOWN,
    objectId = objectId,
)

fun ListProfileModelResponse.toDomain() = UsersListDomainModel(
    result = results.map { it.toDomain() }
)
