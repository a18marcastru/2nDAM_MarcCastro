package com.example.microservicesmanager.network.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.microservicesmanager.network.database.entities.ProfileEntity

@Dao
interface ProfileDao {
    @Query("Select * From profile_table")
    suspend fun getAllQuotes():List<ProfileEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(profiles:List<ProfileEntity>)
}