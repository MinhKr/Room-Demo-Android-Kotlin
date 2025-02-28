package com.minhldn.roomdemo.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tasks",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userTaskId"]
    )
    ]
)

data class Task(
    @PrimaryKey(autoGenerate = true) val taskId: Int = 0,
    val taskName: String,
    val userTaskId: Int,
    val isCompleted: Boolean
)
