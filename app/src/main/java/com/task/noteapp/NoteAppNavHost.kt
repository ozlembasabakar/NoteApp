package com.task.noteapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.task.noteapp.addoreditscreen.AddOrEditScreen
import com.task.noteapp.notesscreen.NotesScreen
import com.task.noteapp.notesscreen.NotesScreenViewModel

@Composable
fun NoteAppNavHost() {

    val navController = rememberNavController()

    val notesScreenViewModel: NotesScreenViewModel = hiltViewModel()
    val notesScreenViewState by notesScreenViewModel.notes.collectAsState()

    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = Screen.NotesScreen.route
    ) {
        composable(Screen.NotesScreen.route) {
            NotesScreen(
                note = notesScreenViewState.notes,
                navController = navController,
                modifier = Modifier
            )
        }
        composable(Screen.AddOrEditScreen.route) {
            AddOrEditScreen(
                navController = navController,
                modifier = Modifier
            )
        }
    }
}

sealed class Screen(
    val route: String,
) {
    object NotesScreen : Screen("NotesScreen")
    object AddOrEditScreen : Screen("AddOrEditScreen")
}