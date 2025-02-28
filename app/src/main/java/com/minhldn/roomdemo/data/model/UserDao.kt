package com.minhldn.roomdemo.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    fun insertUser(users: User)

    @Update
    fun updateUser(users: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT * FROM users")
    fun getAllUser(): List<User>

    @Insert
    fun insertTask(task: Task)
}