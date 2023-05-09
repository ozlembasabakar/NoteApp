package com.task.noteapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val title: String = "",
    @ColumnInfo val description: String = "",
    @ColumnInfo val imageUrl: String = "",
    @ColumnInfo val timestamp: Long = 0
)