package com.rooms.users.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rooms.users.data.dao.UserDao
import com.rooms.users.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}