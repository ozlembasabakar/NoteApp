package com.example.addoreditscreen

import android.os.Build
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.AddNewNoteUseCase
import com.example.domain.GetNoteByIdUseCase
import com.example.model.model.Note
import com.example.model.model.AddOrEditModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.inject.Inject

@HiltViewModel
class AddOrEditScreenViewModel @Inject constructor(
    private val addNewNoteUseCase: AddNewNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val currentNoteId = savedStateHandle.get<Int>("id") ?: 0

    private val currentDate: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val dateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        val currentDate = dateTime.format(formatter)

        currentDate
    } else {
        ""
    }

    val addOrEditModel = AddOrEditModel()

    val addOrEditAction = MutableSharedFlow<AddOrEditAction>()

    init {
        editNote()
    }

    fun addNote() {
        viewModelScope.launch {
            if (validate()) {
                addNewNoteUseCase(
                    Note(
                        id = currentNoteId,
                        title = addOrEditModel.title.value,
                        imageUrl = addOrEditModel.imageUrl.value,
                        description = addOrEditModel.note.value,
                        date = currentDate
                    )
                )
            }
            addOrEditAction.emit(AddOrEditAction.NavigateBack)
        }
    }

    private fun editNote() {
        if (currentNoteId != 0) {
            viewModelScope.launch {
                val note = getNoteByIdUseCase(currentNoteId)

                addOrEditModel.title.value = note.title
                addOrEditModel.imageUrl.value = note.imageUrl
                addOrEditModel.note.value = note.description
                addOrEditModel.date.value = currentDate
            }
        }
    }

    private fun validate(): Boolean {
        return addOrEditModel.title.value.isNotBlank() || addOrEditModel.imageUrl.value.isNotBlank() || addOrEditModel.note.value.isNotBlank()
    }
}

sealed class AddOrEditAction {
    object NavigateBack : AddOrEditAction()
}