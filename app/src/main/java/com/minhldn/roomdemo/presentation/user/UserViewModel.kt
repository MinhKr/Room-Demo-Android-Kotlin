package com.minhldn.roomdemo.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.minhldn.roomdemo.data.model.User
import com.minhldn.roomdemo.data.repository.UserRepositoryImpl

class UserViewModel(private val userRepository: UserRepositoryImpl) : ViewModel() {
    private val _users = MutableLiveData<List<User>>(listOf())
    val users: LiveData<List<User>> get() = _users

    fun insertUser(user: User) {
        userRepository.insertUser(user)
        getUserData()
    }

    fun updateUser(user: User) {
        userRepository.updateUser(user)
        getUserData()
    }

    fun deleteUser(user: User) {
        userRepository.deleteUser(user)
        getUserData()
    }

    fun getUserData() {
        userRepository.getAllUser().also {
            _users.postValue(it)
        }
    }
}