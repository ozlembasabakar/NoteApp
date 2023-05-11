package com.task.noteapp.addoreditscreen

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.task.noteapp.MainActivity
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class BackButtonTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun whenOpenedAddNoteScreen_IsBackButtonDisplayed() {

        composeTestRule.onNodeWithTag("Button").performClick()

        composeTestRule.onNodeWithTag("BackButton").assertExists()
    }

    @Test
    fun whenOpenedAddNoteScreen_IsBackButtonClickable() {

        composeTestRule.onNodeWithTag("Button").performClick()

        composeTestRule.onNodeWithTag("BackButton").assertHasClickAction()
    }
}