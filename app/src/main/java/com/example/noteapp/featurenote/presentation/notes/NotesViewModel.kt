package com.example.noteapp.featurenote.presentation.notes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.featurenote.domain.model.Note
import com.example.noteapp.featurenote.domain.usecase.NoteUseCases
import com.example.noteapp.featurenote.domain.util.NoteOrder
import com.example.noteapp.featurenote.domain.util.NotesEvent
import com.example.noteapp.featurenote.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var lastDeletedNote: Note? = null
    private var getNotesJob: Job? = null

    private var cachedExerciseList = NotesState()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(notesEvent: NotesEvent) {
        when (notesEvent) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == notesEvent.noteOrder::class
                    && state.value.noteOrder.orderType == notesEvent.noteOrder.orderType
                ) {
                    return
                }
                getNotes(notesEvent.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(notesEvent.note)
                    lastDeletedNote = notesEvent.note
                }

            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(lastDeletedNote ?: return@launch)
                    lastDeletedNote = null
                }

            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder)
            .onEach {
                _state.value = state.value.copy(
                    notes = it,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

    fun searchExerciseList(query: String) {
        val listToSearch = if (isSearchStarting) {
            _state.value
        } else {
            cachedExerciseList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                _state.value = cachedExerciseList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.notes.filter {
                it.title.contains(query.trim(), ignoreCase = true)
            }
            if (isSearchStarting) {
                cachedExerciseList = _state.value
                isSearchStarting = false
            }
            _state.value = state.value.copy(
                notes = results
            )
            Log.d("Ozlemwashere", "$results") // aramayı yspıyor ama ekrana yazdıramadım
            isSearching.value = true
        }
    }
}