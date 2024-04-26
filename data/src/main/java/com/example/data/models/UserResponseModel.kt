package com.example.data.models

import com.google.gson.annotations.SerializedName

data class UserResponseModel(
    @SerializedName("username") val userEmail: String,
    @SerializedName("password") val userPassword: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
)
