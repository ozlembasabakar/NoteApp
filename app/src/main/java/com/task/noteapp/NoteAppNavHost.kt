package com.task.noteapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.task.noteapp.addoreditscreen.AddOrEditScreen
import com.task.noteapp.addoreditscreen.AddOrEditScreenViewModel
import com.task.noteapp.notesscreen.NotesScreen
import com.task.noteapp.notesscreen.NotesScreenViewModel
import kotlinx.coroutines.flow.collectLatest

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

            Log.d("ozlem was here", notesScreenViewState.notes.last().title)

            NotesScreen(
                note = notesScreenViewState.notes,
                navController = navController,
                modifier = Modifier
            )
        }
        composable(Screen.AddOrEditScreen.route) {

            val addOrEditScreenViewModel: AddOrEditScreenViewModel = hiltViewModel()
            val lifecycle = LocalLifecycleOwner.current.lifecycle

            LaunchedEffect(Unit) {
                lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                    addOrEditScreenViewModel.addOrEditAction.collectLatest {
                        navController.navigate(Screen.NotesScreen.route)
                    }
                }
            }

            AddOrEditScreen(
                addOrEditScreenModel = addOrEditScreenViewModel.addOrEditScreenModel,
                addNoteEvent = {
                    addOrEditScreenViewModel.addNote()
                },
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