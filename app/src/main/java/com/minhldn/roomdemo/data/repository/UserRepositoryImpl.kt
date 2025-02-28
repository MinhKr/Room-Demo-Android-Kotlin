package com.minhldn.roomdemo.data.repository

import com.minhldn.roomdemo.data.model.User
import com.minhldn.roomdemo.data.model.UserDao

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    override fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override fun getAllUser(): List<User> {
        return userDao.getAllUser()
    }
}