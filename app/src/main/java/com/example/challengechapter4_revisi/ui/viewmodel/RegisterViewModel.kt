package com.example.challengechapter4_revisi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.challengechapter4_revisi.data.user.DataUserManager
import kotlinx.coroutines.launch

class RegisterViewModel(private val pref: DataUserManager): ViewModel() {
    fun saveUser(username: String, email: String, password: String){
        viewModelScope.launch {
            pref.setUsername(username)
            pref.setPassword(password)
            pref.setEmail(email)
        }
    }

}
