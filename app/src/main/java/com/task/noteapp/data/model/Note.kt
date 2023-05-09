package com.task.noteapp.data.model

data class Note(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val timestamp: Long = 0
)