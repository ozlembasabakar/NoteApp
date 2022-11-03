package com.example.noteapp.featurenote.domain.usecase

import com.example.noteapp.featurenote.domain.model.Note
import com.example.noteapp.featurenote.domain.repository.NoteRepository

class GetNote(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(id: Int): Note? {
        return noteRepository.getNotesById(id = id)
    }
}