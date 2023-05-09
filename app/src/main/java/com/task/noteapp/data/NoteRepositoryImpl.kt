package com.task.noteapp.data

import com.task.noteapp.RemoteDatasource
import com.task.noteapp.data.model.Note
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteDatasource
) : NoteRepository {
    override fun getAllNotes(): List<Note> {
        return remoteDatasource.datasource
    }

    override fun addNewNote(note: Note): MutableList<Note> {
        remoteDatasource.datasource.add(note)
        return remoteDatasource.datasource
    }
}