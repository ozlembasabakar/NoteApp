package com.task.noteapp.addoreditscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.NoteRepository
import com.task.noteapp.data.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddOrEditScreenViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
) : ViewModel() {

    val addOrEditScreenModel = AddOrEditScreenModel()

    val addOrEditAction = MutableSharedFlow<AddOrEditAction>()

    fun addNote() {
        viewModelScope.launch {
            noteRepository.addNewNote(
                Note(
                    title = addOrEditScreenModel.title.value,
                    imageUrl = addOrEditScreenModel.imageUrl.value,
                    description = addOrEditScreenModel.note.value,
                )
            )

            addOrEditAction.emit(AddOrEditAction.NavigateBack)
        }
    }
}

sealed class AddOrEditAction {
    object NavigateBack : AddOrEditAction()
}