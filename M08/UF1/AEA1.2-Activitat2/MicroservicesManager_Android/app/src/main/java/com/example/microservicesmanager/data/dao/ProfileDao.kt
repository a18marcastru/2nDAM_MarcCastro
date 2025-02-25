package com.example.microservicesmanager.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.microservicesmanager.data.model.Profile

@Dao
interface ProfileDao {
    @Query("SELECT * FROM Profile")
    suspend fun getProfilesAll() : List<Profile>?

    @Insert
    suspend fun insertProfile(item: Profile)

    @Query("UPDATE profile SET predetermined = :reset")
    suspend fun clearAllPredetermined(reset: Int)

    @Query("UPDATE Profile SET predetermined = :isPredetermined WHERE id = :id")
    suspend fun updatePredetermined(id: Int, isPredetermined: Int)

    @Query("DELETE FROM Profile WHERE id = :id")
    suspend fun deleteProfile(id: Int)
}