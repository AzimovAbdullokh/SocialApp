package com.example.data.mappers

import com.example.data.models.UserResponseModel
import com.example.domain.models.UserSignDomainModel

fun UserSignDomainModel.toData() = UserResponseModel(
    userEmail = userEmail,
    userPassword = userPassword,
    firstName = userFirstName,
    lastName = userLastname,
)