package com.task.noteapp.notesscreen

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.task.noteapp.MainActivity
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class AddNewButtonTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun addButtonIsVisible() {
        composeTestRule.onNodeWithTag("Button").assertExists()
    }

    @Test
    fun addButtonIsClickable() {
        composeTestRule.onNodeWithTag("Button").performClick()
    }

    @Test
    fun whenPressedAddButton_AddNoteScreenIsOpened() {
        composeTestRule.onNodeWithTag("Button").performClick()
        Thread.sleep(500)
        composeTestRule.onNodeWithTag("AddOrEditScreen").assertExists()
    }
}