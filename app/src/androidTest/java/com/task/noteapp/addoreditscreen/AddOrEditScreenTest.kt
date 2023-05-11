package com.task.noteapp.addoreditscreen

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.task.noteapp.MainActivity
import com.example.model.model.Note
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AddOrEditScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun clearDatabaseBeforeStart() {
        composeTestRule.onAllNodes(hasTestTag("Delete")).apply {
            fetchSemanticsNodes().forEachIndexed { i, _ ->
                get(i).performClick()
            }
        }
    }

    private val note = Note(
        title = "Title",
        description = "Note",
        imageUrl = "Url",
        date = "Date"
    )

    @Test
    fun whenPressedAddButton_EnteredNotesDetail_IsInputDisplayedOnTheScreen() {

        composeTestRule.onNodeWithTag("Button").performClick()

        composeTestRule.onNodeWithTag("Title").performClick()
        composeTestRule.onNodeWithTag("Title").performTextInput(note.title)

        composeTestRule.onNodeWithTag("AddOrEditScreen").assertExists(note.title)
    }

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
    fun whenPressedAddButton_DoingNothing_IsDisplayedOnTheScreen() {

        composeTestRule.onNodeWithTag("Button").performClick()

        composeTestRule.onNodeWithTag("BackButton").performClick()

        composeTestRule.onNodeWithTag("Card").assertDoesNotExist()

    }

    @Test
    fun whenPressedAddButton_ChangedNotesDetail_IsChangesDisplayedOnTheScreen() {

        composeTestRule.onNodeWithTag("Button").performClick()

        composeTestRule.onNodeWithTag("Title").performClick()
        composeTestRule.onNodeWithTag("Title").performTextInput(note.title)

        composeTestRule.onNodeWithTag("BackButton").performClick()

        composeTestRule.onNodeWithTag("Card").performClick()

        composeTestRule.onNodeWithTag("Title").performClick()
        composeTestRule.onNodeWithTag("Title").performTextReplacement(note.date)

        composeTestRule.onNodeWithTag("BackButton").performClick()

        composeTestRule.onAllNodes(hasTestTag("Card")).apply {
            fetchSemanticsNodes().forEachIndexed { i, _ ->
                get(i).assertTextContains(note.date)
            }
        }
    }
}