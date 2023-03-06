package com.example.fitbit2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "input_table")
data class InputEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "sleepType") val sleepType: String?,
    @ColumnInfo(name = "sleepHours") val sleepHours: String?,
)