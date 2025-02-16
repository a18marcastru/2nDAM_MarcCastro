package com.rooms.users.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rooms.users.data.model.User
import com.rooms.users.data.model.UserList

@Dao
interface UserDao {
    @Query("SELECT * FROM Users")
    suspend fun getUserAll() : List<UserList>?

//    @Query("SELECT * FROM Users WHERE email = :email AND password = :password")
//    suspend fun getUser(email: String, password: String) : User?

    @Insert
    suspend fun insertUser(item: User)
}