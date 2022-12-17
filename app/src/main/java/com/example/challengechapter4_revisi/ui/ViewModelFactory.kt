package com.example.challengechapter4_revisi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapter4_revisi.data.user.DataUserManager
import com.example.challengechapter4_revisi.ui.viewmodel.LoginViewModel
import com.example.challengechapter4_revisi.ui.viewmodel.RegisterViewModel

class ViewModelFactory(private val pref: DataUserManager) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(pref) as T
        }
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }
}