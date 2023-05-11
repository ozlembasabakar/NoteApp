package com.task.noteapp.data

import com.task.noteapp.data.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Int): Note
    suspend fun addNewNote(note: Note)
    suspend fun deleteNote(note: Note)
}
