package com.task.noteapp.notesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.NoteRepository
import com.task.noteapp.data.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesScreenViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
) : ViewModel() {

    private val _notes = MutableStateFlow(NotesScreenViewState())
    val notes = _notes.asStateFlow()

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            noteRepository.getAllNotes().collect { notes ->
                _notes.update { viewState ->
                    viewState.copy(notes = notes)
                }
            }
        }
    }
}

data class NotesScreenViewState(
    val notes: List<Note> = emptyList(),
)