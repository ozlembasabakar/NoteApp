package com.task.noteapp.notesscreen

import com.example.domain.DeleteNoteUseCase
import com.example.domain.GetAllNotesUseCase
import com.example.model.model.Note
import com.example.notesscreen.NotesScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


@OptIn(ExperimentalCoroutinesApi::class)
internal class NotesScreenViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getAllNotesUseCase: GetAllNotesUseCase = mock()
    private val deleteNoteUseCase: DeleteNoteUseCase = mock()
    private fun notesScreenViewModel(): NotesScreenViewModel =
        NotesScreenViewModel(getAllNotesUseCase, deleteNoteUseCase)

    @Test
    fun `when opened the app, should get all notes`() = runTest {
        val viewModel = notesScreenViewModel()

        verify(getAllNotesUseCase).invoke()
    }

    @Test
    fun `when the note deleted, should delete from repository`() = runTest {
        val viewModel = notesScreenViewModel()
        val note = mock<Note>()

        viewModel.deleteNote(note)

        verify(deleteNoteUseCase).invoke(note)
    }
}