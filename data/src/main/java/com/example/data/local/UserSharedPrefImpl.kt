package com.example.data.local

import android.content.Context
import com.example.domain.models.UserProfileDomainModel
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserSharedPrefImpl @Inject constructor(
    @ApplicationContext context: Context,
) : UserSharedPref {

    private val sharedPref by lazy(LazyThreadSafetyMode.NONE) {
        context.getSharedPreferences(
            USER_SHARED_PREF_NAME,
            Context.MODE_PRIVATE,
        )
    }

    override suspend fun saveIsUserSigned() {
        sharedPref.edit().putBoolean(USER_SAVE_TAG, true).apply()
    }

    override fun observeIsUserSigned(): Boolean {
        return sharedPref.getBoolean(USER_SAVE_TAG, false)
    }

    override fun saveCurrentUser(user: UserProfileDomainModel) {
        val currentUser = Gson().toJson(user)
        sharedPref.edit().putString(USER_ID_TAG, currentUser).apply()
    }

    override fun getCurrentUser(): UserProfileDomainModel {
        val json = sharedPref.getString(USER_ID_TAG, null)
        return Gson().fromJson(json, UserProfileDomainModel::class.java)
    }

    override fun clearAll() {
        sharedPref.edit().clear().apply()
    }

    companion object {
        private const val USER_SAVE_TAG = "USER_SAVE_TAG"
        private const val USER_ID_TAG = "USER_ID_TAG"
        private const val USER_SHARED_PREF_NAME = "USER_SHARED_PREF_NAME"
    }
}