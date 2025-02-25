package com.example.microservicesmanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.microservicesmanager.data.dao.ProfileDao
import com.example.microservicesmanager.data.model.Profile

@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class MicroserviceDB : RoomDatabase() {


    abstract fun profileDao(): ProfileDao
}
