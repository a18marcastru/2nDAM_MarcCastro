package com.rooms.users.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rooms.users.data.model.User
import com.rooms.users.data.model.UserList

@Dao
interface UserDao {
    @Query("SELECT * FROM Users WHERE predetermined = 1 LIMIT 1")
    suspend fun getUser() : List<User>?

    @Query("UPDATE Users SET predetermined = :predetermined WHERE id = :id")
    suspend fun updatePredetermined(id: Int, predetermined: Boolean)

    @Query("UPDATE Users SET predetermined = :predetermined WHERE email = :email AND password = :password")
    suspend fun getLogin(email: String, password: String, predetermined: Boolean): Int

    @Insert
    suspend fun insertUser(item: User)

    @Query("DELETE FROM Users WHERE id = :id")
    suspend fun deleteUser(id: Int)

    @Query("DELETE FROM Users")
    suspend fun deleteAll()
}