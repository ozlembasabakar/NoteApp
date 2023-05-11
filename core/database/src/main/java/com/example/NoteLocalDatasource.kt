package com.example

import com.example.dao.NoteDao
import com.example.model.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteLocalDatasource @Inject constructor(
    private val noteDao: NoteDao,
) {

    fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    suspend fun getNoteById(id: Int): Note {
        return noteDao.getNoteById(id)
    }

    suspend fun addNewNote(note: Note) {
        noteDao.addNewNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }
}