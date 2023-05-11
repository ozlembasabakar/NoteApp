package com.example.notesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.DeleteNoteUseCase
import com.example.domain.GetAllNotesUseCase
import com.example.model.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesScreenViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : ViewModel() {

    private val _notes = MutableStateFlow(NotesScreenViewState())
    val notes = _notes.asStateFlow()

    val notesAction = MutableSharedFlow<NotesAction>()

    init {
        getAllNotes()
    }

    private fun getAllNotes() {
        viewModelScope.launch {
            getAllNotesUseCase().collect { notes ->
                _notes.update { viewState ->
                    viewState.copy(notes = notes)
                }
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }

    fun openAddNewNoteScreen() {
        viewModelScope.launch {
            notesAction.emit(NotesAction.OpenAddNewNoteScreen)
        }
    }

    fun openNoteDetail(note: Note) {
        viewModelScope.launch {
            notesAction.emit(NotesAction.OpenNoteDetail(note.id))
        }
    }
}

data class NotesScreenViewState(
    val notes: List<Note> = emptyList(),
)

sealed class NotesAction {
    object OpenAddNewNoteScreen : NotesAction()
    data class OpenNoteDetail(val id: Int) : NotesAction()
}