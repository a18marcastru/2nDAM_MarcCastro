package com.example.microservicesmanager.network.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.microservicesmanager.network.database.dao.ProfileDao
import com.example.microservicesmanager.network.database.entities.ProfileEntity

@Database(entities = [ProfileEntity::class], version = 1)
abstract class ProfileDatabase: RoomDatabase() {

    abstract fun getProfileDao(): ProfileDao
}