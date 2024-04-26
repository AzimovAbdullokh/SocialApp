package com.example.socialapp.presentation.mappers

import com.example.domain.models.UserAvatarDomainModel
import com.example.domain.models.UserProfileDomainModel
import com.example.socialapp.presentation.models.CurrentUserUiModel
import com.example.socialapp.presentation.models.UserAvatarUiModel

fun UserProfileDomainModel.toUio() = CurrentUserUiModel(
    username = username,
    userBio = userBio,
    firstName = firstName,
    userLocation = userLocation,
    userAvatar = userAvatar.toUio(),
    lastName = lastName,
    objectId = objectId,
)

fun UserAvatarDomainModel.toUio() = UserAvatarUiModel(
    name = name,
    type = type,
    url = url,
)