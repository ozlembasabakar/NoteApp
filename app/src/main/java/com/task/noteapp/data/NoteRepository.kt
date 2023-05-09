package com.task.noteapp.data

import com.task.noteapp.data.model.Note

interface NoteRepository {
    fun getAllNotes(): List<Note>
    fun addNewNote(note: Note): MutableList<Note>
}
