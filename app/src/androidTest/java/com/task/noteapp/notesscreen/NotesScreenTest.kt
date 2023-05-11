package com.task.noteapp.notesscreen

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.task.noteapp.MainActivity
import com.task.noteapp.data.model.Note
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class NotesScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val note = Note(
        title = "Title",
        description = "Note",
        imageUrl = "Url",
        date = "Date"
    )

    @Test
    fun whenPressedAddButton_EnteredNotesDetail_IsDisplayedOnTheScreen() {

        composeTestRule.onNodeWithTag("Button").performClick()

        composeTestRule.onNodeWithTag("Title").performClick()
        composeTestRule.onNodeWithTag("Title").performTextInput(note.title)

        composeTestRule.onNodeWithTag("BackButton").performClick()

        composeTestRule.onAllNodes(hasTestTag("Card")).apply {
            fetchSemanticsNodes().forEachIndexed { i, _ ->
                get(i).assertTextContains(note.title)
            }
        }
    }

    @Test
    fun whenPressedDeleteIcon_IsNoteDeletedFromList() {

        composeTestRule.onNodeWithTag("Button").performClick()

        composeTestRule.onNodeWithTag("Title").performClick()
        composeTestRule.onNodeWithTag("Title").performTextInput(note.title)

        composeTestRule.onNodeWithTag("BackButton").performClick()

        composeTestRule.onNodeWithTag("Delete").performClick()

        composeTestRule.onNodeWithTag("Card").assertDoesNotExist()
    }

    @Test
    fun isNotesListScrollable() {

        repeat(15) {
            composeTestRule.onNodeWithTag("Button").performClick()

            composeTestRule.onNodeWithTag("Title").performClick()
            composeTestRule.onNodeWithTag("Title").performTextInput(note.title)

            composeTestRule.onNodeWithTag("BackButton").performClick()
        }

        composeTestRule.onNodeWithTag("List").assert(hasScrollAction())
    }


}