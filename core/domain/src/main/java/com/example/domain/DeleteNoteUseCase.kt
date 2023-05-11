package com.example.domain

import com.example.data.NoteRepository
import com.example.model.model.Note
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(note: Note) {
        return noteRepository.deleteNote(note)
    }
}