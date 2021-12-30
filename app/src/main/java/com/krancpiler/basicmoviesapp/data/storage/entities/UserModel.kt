package com.krancpiler.basicmoviesapp.data.storage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val sessionId: String,
    val userName: String
)