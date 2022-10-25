package com.example.noteapp.featurenote.domain.usecase

import com.example.noteapp.featurenote.domain.model.Note
import com.example.noteapp.featurenote.domain.repository.NoteRepository
import com.example.noteapp.featurenote.domain.util.NoteOrder
import com.example.noteapp.featurenote.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val noteRepository: NoteRepository,
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending)
    ): Flow<List<Note>> {
        return noteRepository.getNotes().map {notes ->
            when(noteOrder.orderType) {
                is OrderType.Ascending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date ->notes.sortedBy { it.timestamp }
                    }
                }
                is OrderType.Descending -> {
                    when(noteOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date ->notes.sortedByDescending { it.timestamp }
                    }
                }
            }
        }
    }
}