package com.example.challengechapter4_revisi.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.challengechapter4_revisi.data.user.DataUserManager
import kotlinx.coroutines.launch

class LoginViewModel(private val pref: DataUserManager): ViewModel() {

    fun getUser(): LiveData<String>{
        return pref.getUsername().asLiveData()
    }

    fun getPassword(): LiveData<String>{
        return pref.getPassword().asLiveData()
    }

    fun getEmail(): LiveData<String>{
        return pref.getEmail().asLiveData()
    }

    fun setIsLogin(isLogin:Boolean){
        viewModelScope.launch {
            pref.setIsLogin(isLogin)
        }
    }

    fun getIsLogin(): LiveData<Boolean> {
        return pref.getIsLogin().asLiveData()
    }

}
