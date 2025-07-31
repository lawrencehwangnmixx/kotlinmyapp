package com.example.myapplicationnew.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index



@Entity(tableName = "User",
    indices = [Index(value = ["account"], unique = true),
        Index(value = ["username"], unique = true)
    ] // 👈 這行讓 account 唯一
)

data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val account : String,
    val password : String,
    val username : String

)

