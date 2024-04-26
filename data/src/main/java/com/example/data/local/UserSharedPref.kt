package com.example.data.local

interface UserSharedPref {
    suspend fun saveIsUserSigned()
    fun observeIsUserSigned(): Boolean
}