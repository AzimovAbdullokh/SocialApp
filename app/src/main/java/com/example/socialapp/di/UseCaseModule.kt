package com.example.socialapp.di

import com.example.domain.repository.UserAuthRepository
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
}