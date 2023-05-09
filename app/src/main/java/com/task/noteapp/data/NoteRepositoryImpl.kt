package com.task.noteapp.data

import android.util.Log
import com.task.noteapp.RemoteDatasource
import com.task.noteapp.data.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val remoteDatasource: RemoteDatasource,
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return remoteDatasource.datasource
    }

    override suspend fun addNewNote(note: Note) {
        val list = remoteDatasource.datasource.value
        val newList = mutableListOf<Note>()
        newList.addAll(list)
        newList.add(note)
        Log.d("ozlem was here", list.toString())
        remoteDatasource.datasource.emit(newList)
    }
}