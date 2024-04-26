package com.example.socialapp.di

import com.example.domain.repository.UserAuthRepository
import com.example.domain.repository.UserProfileRepository
import com.example.domain.usecase.GetAllUsersUseCase
import com.example.domain.usecase.GetAllUsersUseCaseImpl
import com.example.domain.usecase.GetCurrentUserProfileUseCase
import com.example.domain.usecase.GetCurrentUserProfileUseCaseImpl
import com.example.domain.usecase.UserLoginUseCase
import com.example.domain.usecase.UserLoginUseCaseImpl
import com.example.domain.usecase.UserRegisterUseCase
import com.example.domain.usecase.UserRegisterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideUserRegisterUseCase(
        authRepository: UserAuthRepository,
    ): UserRegisterUseCase = UserRegisterUseCaseImpl(authRepository)

    @Provides
    fun provideUserLoginUseCase(
        authRepository: UserAuthRepository,
    ): UserLoginUseCase = UserLoginUseCaseImpl(authRepository)

    @Provides
    fun provideGetCurrentUserProfileUseCase(
        userProfileRepository: UserProfileRepository,
    ): GetCurrentUserProfileUseCase = GetCurrentUserProfileUseCaseImpl(userProfileRepository)

    @Provides
    fun provideGetAllUsersUseCase(
        userProfileRepository: UserProfileRepository,
    ): GetAllUsersUseCase = GetAllUsersUseCaseImpl(userProfileRepository)
}