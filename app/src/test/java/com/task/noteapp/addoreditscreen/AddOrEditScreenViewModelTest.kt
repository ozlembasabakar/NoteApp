package com.task.noteapp.addoreditscreen

import androidx.lifecycle.SavedStateHandle
import com.example.addoreditscreen.AddOrEditScreenViewModel
import com.example.domain.AddNewNoteUseCase
import com.example.domain.GetNoteByIdUseCase
import com.example.model.model.Note
import com.task.noteapp.notesscreen.MainDispatcherRule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
internal class AddOrEditScreenViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val savedStateHandle: SavedStateHandle = Mockito.mock<SavedStateHandle>()
    private val addNewNoteUseCase: AddNewNoteUseCase= Mockito.mock()
    private val getNoteByIdUseCase: GetNoteByIdUseCase = Mockito.mock()
    private val note: Note = Mockito.mock<Note>()

    private fun addOrEditScreenViewModel(): AddOrEditScreenViewModel =
        AddOrEditScreenViewModel(addNewNoteUseCase, getNoteByIdUseCase, savedStateHandle)

    @Test
    fun `when opened the add new note screen, should add note`() = runTest {
        val viewModel = addOrEditScreenViewModel()
        val note = note

        CoroutineScope(Dispatchers.Default).launch {
            Mockito.verify(addNewNoteUseCase)
        }
    }

    @Test
    fun `when opened a note, should edit note`() = runTest {
        val viewModel = addOrEditScreenViewModel()
        val note = note

        CoroutineScope(Dispatchers.Default).launch {
            Mockito.verify(getNoteByIdUseCase(note.id))
        }
    }
}