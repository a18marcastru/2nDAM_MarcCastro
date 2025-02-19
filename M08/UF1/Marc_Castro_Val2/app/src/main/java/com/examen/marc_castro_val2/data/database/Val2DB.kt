package com.examen.marc_castro_val2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.examen.marc_castro_val2.data.dao.Val2Dao
import com.examen.marc_castro_val2.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class Val2DB : RoomDatabase() {
    abstract fun val2Dao(): Val2Dao
}