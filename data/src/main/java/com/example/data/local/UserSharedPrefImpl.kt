package com.example.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserSharedPrefImpl @Inject constructor(
    @ApplicationContext context: Context,
) : UserSharedPref {

    private val sharedPref = context.getSharedPreferences(
        USER_SHARED_PREF_NAME,
        Context.MODE_PRIVATE,
    )

    override suspend fun saveIsUserSigned() {
        sharedPref.edit().putBoolean(USER_SAVE_TAG, true).apply()
    }

    override fun observeIsUserSigned(): Boolean {
        return sharedPref.getBoolean(USER_SAVE_TAG, false)
    }

    companion object {
        private const val USER_SAVE_TAG = "USER_SAVE_TAG"
        private const val USER_SHARED_PREF_NAME = "USER_SHARED_PREF_NAME"
    }
}