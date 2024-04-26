package com.example.domain.usecase

import com.example.domain.models.UsersListDomainModel

interface GetAllUsersUseCase {
    suspend operator fun invoke(): Result<UsersListDomainModel>
}