package com.example.noteapp.featurenote.domain.usecase

import com.example.noteapp.featurenote.domain.model.InvalidNoteException
import com.example.noteapp.featurenote.domain.model.Note
import com.example.noteapp.featurenote.domain.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isEmpty()) {
            throw InvalidNoteException("The title of the note can not be empty.")
        }
        if (note.content.isEmpty()) {
            throw InvalidNoteException("The content of the note can not be empty.")
        }
        noteRepository.insertNote(note=note)
    }
}