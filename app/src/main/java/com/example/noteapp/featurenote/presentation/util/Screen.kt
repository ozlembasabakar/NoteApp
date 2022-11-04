package com.example.noteapp.featurenote.presentation.util

sealed class Screen(
    val route: String,
) {
    object NotesScreen : Screen("notesScreen")
    object AddOrEditNoteScreen : Screen("addOrEditNoteScreen")
}
