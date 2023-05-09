package com.task.noteapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.task.noteapp.addoreditscreen.AddOrEditScreen
import com.task.noteapp.components.CardModel
import com.task.noteapp.notesscreen.NotesScreen

@Composable
fun NoteAppNavHost() {

    val navController = rememberNavController()

    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = Screen.NotesScreen.route
    ) {
        composable(Screen.NotesScreen.route) {
            NotesScreen(
                cardModel = CardModel(
                    title = "Title",
                    description = "Description",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX_myk5jX77-Ljy3cvcOWEVAcS_QuVL3DTXKYUmpz8hYCnzWj7j6tbd8almtUsQSxSXe0&usqp=CAU"
                ),
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