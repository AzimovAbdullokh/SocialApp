package com.example.data.mappers

import com.example.data.models.UserAnswerResponseModel
import com.example.domain.models.UserAnswerDomainModel

fun UserAnswerResponseModel.toDomain() = UserAnswerDomainModel(
    objectId = objectId, userAvatar = userAvatar
)
