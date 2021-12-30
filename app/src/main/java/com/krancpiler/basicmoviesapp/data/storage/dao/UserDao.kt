package com.krancpiler.basicmoviesapp.data.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.krancpiler.basicmoviesapp.data.storage.entities.UserModel

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUser(): UserModel

    @Insert
    suspend fun insertUser (user: UserModel)

    @Delete
    suspend fun delete(user: UserModel)
}