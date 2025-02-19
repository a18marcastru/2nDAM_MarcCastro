package com.examen.marc_castro_val2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.examen.marc_castro_val2.data.model.User

@Dao
interface Val2Dao {
    @Query("SELECT * FROM User")
    suspend fun getUser() : List<User>?

    @Insert
    suspend fun insertUser(item: User)

    @Query("DELETE FROM User")
    suspend fun deleteAll()
}