package com.example.data

import com.example.NoteLocalDatasource
import com.example.model.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val localDatasource: NoteLocalDatasource,
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return localDatasource.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note {
        return localDatasource.getNoteById(id)
    }

    override suspend fun addNewNote(note: Note) {
        localDatasource.addNewNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        localDatasource.deleteNote(note)
    }
}