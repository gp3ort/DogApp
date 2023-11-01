package com.ort.dogadoption.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedInfoViewModel: ViewModel() {
    private val _usernamePhoto = MutableLiveData<String>()
    val userNamePhoto: LiveData<String> = _usernamePhoto

    private val _isDarkMode = MutableLiveData<Boolean>()
    val isDarkMode: LiveData<Boolean> = _isDarkMode

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    fun setUsernamePhoto(userNamePhoto: String) {
        _usernamePhoto.value = userNamePhoto
    }

    fun setDarkMode(isDarkMode: Boolean) {
        _isDarkMode.value = isDarkMode
    }

    fun setUsername(userName: String) {
        _userName.value = userName
    }
}