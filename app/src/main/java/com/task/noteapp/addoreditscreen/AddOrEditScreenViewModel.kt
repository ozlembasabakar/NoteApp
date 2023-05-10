package com.task.noteapp.addoreditscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.NoteRepository
import com.task.noteapp.data.model.AddOrEditModel
import com.task.noteapp.data.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddOrEditScreenViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
) : ViewModel() {

    val addOrEditModel = AddOrEditModel()

    val addOrEditAction = MutableSharedFlow<AddOrEditAction>()

    fun addNote() {
        viewModelScope.launch {
            if (validate()) {
                noteRepository.addNewNote(
                    Note(
                        title = addOrEditModel.title.value,
                        imageUrl = addOrEditModel.imageUrl.value,
                        description = addOrEditModel.note.value,
                    )
                )
            }
            addOrEditAction.emit(AddOrEditAction.NavigateBack)
        }
    }

    private fun validate(): Boolean {
        return addOrEditModel.title.value.isNotBlank() || addOrEditModel.imageUrl.value.isNotBlank() || addOrEditModel.note.value.isNotBlank()
    }
}

sealed class AddOrEditAction {
    object NavigateBack : AddOrEditAction()
}