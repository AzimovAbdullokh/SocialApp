package com.example.data.service

import com.example.data.models.ListProfileModelResponse
import com.example.data.models.UserProfileResponseModel
import com.example.data.models.UserResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val CLASS_USER = "classes/_User"

interface UserService {

    @POST(CLASS_USER)
    suspend fun registerNewUser(
        @Body userResponseModel: UserResponseModel,
    ): Response<UserProfileResponseModel>

    @POST("login")
    suspend fun loginUser(
        @Query("username") userEmail: String,
        @Query("password") userPassword: String,
    ): Response<UserProfileResponseModel>

    @GET("users")
    suspend fun getUserProfile(
        @Query("where") params: String,
    ): Response<ListProfileModelResponse>

    @GET("users")
    suspend fun getAllUsers(): Response<ListProfileModelResponse>
}