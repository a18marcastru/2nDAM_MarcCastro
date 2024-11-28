package com.example.microservicesmanager.network.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "label") val label: String,
    @ColumnInfo(name = "color") val color: String,
    @ColumnInfo(name = "host") val host: Int,
    @ColumnInfo(name = "port") val port: Int,
    @ColumnInfo(name = "predetermined") val predetermined: Boolean
)