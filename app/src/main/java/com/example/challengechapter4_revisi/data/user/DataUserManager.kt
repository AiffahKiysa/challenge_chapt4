package com.example.challengechapter4_revisi.data.user

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataUserManager(private val context: Context){

    companion object {
        private const val DATASTORE_NAME = "login_preferences"
        private val USERNAME = stringPreferencesKey("username_key")
        private val PASSWORD = stringPreferencesKey("password_key")
        private val EMAIL = stringPreferencesKey("email_key")
        private val IS_LOGIN = booleanPreferencesKey("is_login_key")

        private val Context.dataUser by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }

    suspend fun setUsername(username: String) {
        context.dataUser.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    suspend fun setPassword(password: String) {
        context.dataUser.edit { preferences ->
            preferences[PASSWORD] = password
        }
    }

    suspend fun setEmail(email: String) {
        context.dataUser.edit { preferences ->
            preferences[EMAIL] = email
        }
    }


    suspend fun setIsLogin(isLogin: Boolean) {
        context.dataUser.edit { preferences ->
            preferences[IS_LOGIN] = isLogin
        }
    }

    suspend fun getPreferences(context: Context): Flow<Preferences> {
        return context.dataUser.data
    }


    fun getPassword(): Flow<String>{
        return context.dataUser.data.map { preferences ->
            preferences[PASSWORD] ?: ""
        }
    }

    fun getIsLogin(): Flow<Boolean>{
        return context.dataUser.data.map { preferences ->
            preferences[IS_LOGIN] ?: false
        }
    }

    fun getUsername(): Flow<String> {
        return context.dataUser.data.map { preferences ->
            preferences[USERNAME] ?: ""
        }
    }

    fun getEmail(): Flow<String> {
        return context.dataUser.data.map { preferences ->
            preferences[EMAIL] ?: ""
        }
    }
}