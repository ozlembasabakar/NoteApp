package com.task.noteapp.notesscreen

import com.task.noteapp.data.NoteRepository
import com.task.noteapp.data.model.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


@OptIn(ExperimentalCoroutinesApi::class)
internal class NotesScreenViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val noteRepository: NoteRepository = mock<NoteRepository>()
    private fun notesScreenViewModel(): NotesScreenViewModel = NotesScreenViewModel(noteRepository)

    @Test
    fun `when opened the app, should get all notes`() = runTest {
        val viewModel = notesScreenViewModel()

        verify(noteRepository).getAllNotes()
    }

    @Test
    fun `when the note deleted, should delete from repository`() = runTest {
        val viewModel = notesScreenViewModel()
        val note = mock<Note>()

        viewModel.deleteNote(note)

        verify(noteRepository).deleteNote(note)
    }
}