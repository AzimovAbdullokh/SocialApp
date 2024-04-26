package com.example.socialapp.presentation.auth.login

import androidx.compose.runtime.Immutable
import com.example.socialapp.presentation.screens.utils.emptyString

@Immutable
data class LoginUserInfoState(
    val userName: String = emptyString(),
    val userPassword:String = emptyString(),
)