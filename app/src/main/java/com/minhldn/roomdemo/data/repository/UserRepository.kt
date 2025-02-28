package com.minhldn.roomdemo.data.repository

import com.minhldn.roomdemo.data.model.User

interface UserRepository {
    fun insertUser(user : User)
    fun getAllUser() : List<User>
    fun updateUser(user: User)
    fun deleteUser(user: User)
}