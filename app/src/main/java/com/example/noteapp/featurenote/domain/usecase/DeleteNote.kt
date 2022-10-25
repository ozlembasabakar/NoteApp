package com.example.noteapp.featurenote.domain.usecase

import com.example.noteapp.featurenote.domain.model.Note
import com.example.noteapp.featurenote.domain.repository.NoteRepository

class DeleteNote(
    private val noteRepository: NoteRepository,
) {

    suspend operator fun invoke(note: Note) {
        noteRepository.deleteNote(note)
    }
}