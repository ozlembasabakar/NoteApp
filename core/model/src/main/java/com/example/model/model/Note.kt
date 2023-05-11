package com.example.model.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val date: String = "",
    val isEdited: Boolean = false
)