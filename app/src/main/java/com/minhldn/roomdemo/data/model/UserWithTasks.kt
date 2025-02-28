package com.minhldn.roomdemo.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithTasks(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userTaskId"
    )
    val tasks: List<Task>
)
