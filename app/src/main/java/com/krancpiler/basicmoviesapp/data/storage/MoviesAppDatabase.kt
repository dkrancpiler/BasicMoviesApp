package com.krancpiler.basicmoviesapp.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.krancpiler.basicmoviesapp.data.storage.dao.UserDao
import com.krancpiler.basicmoviesapp.data.storage.entities.UserModel

@Database(entities = [UserModel::class], version = 1)
abstract class MoviesAppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}