package com.task.noteapp.data

import com.task.noteapp.data.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val localDatasource: NoteLocalDatasource,
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return localDatasource.getAllNotes()
    }

    override suspend fun addNewNote(note: Note) {
        localDatasource.addNewNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        localDatasource.deleteNote(note)
    }
}