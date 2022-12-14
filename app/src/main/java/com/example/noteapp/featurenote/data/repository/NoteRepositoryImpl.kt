package com.example.noteapp.featurenote.data.repository

import com.example.noteapp.featurenote.data.datasource.NoteDao
import com.example.noteapp.featurenote.domain.model.Note
import com.example.noteapp.featurenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao,
) : NoteRepository {

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes()
    }

    override suspend fun getNotesById(id: Int): Note? {
        return noteDao.getNoteById(id = id)
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note = note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note = note)
    }
}