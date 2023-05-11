package com.example.domain

import com.example.data.NoteRepository
import com.example.model.model.Note
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(id: Int): Note {
        return noteRepository.getNoteById(id)
    }
}