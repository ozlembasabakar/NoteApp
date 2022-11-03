package com.example.noteapp.featurenote.presentation.addoreditnote

import androidx.compose.ui.focus.FocusState

sealed class AddOrEditNoteEvent {
    data class EnteredTitle( val value: String): AddOrEditNoteEvent()
    data class ChangeTitleFocus( val focusState: FocusState): AddOrEditNoteEvent()
    data class EnteredContent( val value: String): AddOrEditNoteEvent()
    data class ChangeContentFocus( val focusState: FocusState): AddOrEditNoteEvent()
    data class ChangeColor( val color: Int): AddOrEditNoteEvent()
    object SaveNote: AddOrEditNoteEvent()

}
