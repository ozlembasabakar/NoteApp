package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.model.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): com.example.dao.NoteDao
}