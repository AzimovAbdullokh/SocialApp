package com.example.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListProfileModelResponse(
    @SerializedName("results")
    val results: List<UserProfileResponseModel>,
)

data class UserProfileResponseModel(
    @SerializedName("objectId") val objectId: String,
    @SerializedName("username") val username: String?,
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("bio") val userBio: String?,
    @SerializedName("location") val userLocation: String?,
    @SerializedName("userAvatar") val userAvatar: UserAvatarResponseModel?,
) : Serializable {
    companion object {
        val UNKNOWN = UserProfileResponseModel(
            "",
            "",
            "",
            "",
            "",
            "",
            UserAvatarResponseModel("", "", ""),
        )
    }
}

data class UserAvatarResponseModel(
    @SerializedName("name") val name: String,
    @SerializedName("__type") val type: String,
    @SerializedName("url") val url: String,
) : Serializable