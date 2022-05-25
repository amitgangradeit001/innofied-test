package com.example.innofiedtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.innofiedtest.UserRepository

class MainUserViewModelFactory constructor(private val userRepository: UserRepository):
ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserRepository::class.java).newInstance(userRepository)
    }

}