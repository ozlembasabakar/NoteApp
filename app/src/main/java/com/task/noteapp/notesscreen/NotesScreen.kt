package com.task.noteapp.notesscreen

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.task.noteapp.components.Button
import com.task.noteapp.components.CardModel
import com.task.noteapp.components.NotesList
import com.task.noteapp.theme.NoteAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesScreen(
    cardModel: CardModel,
    modifier: Modifier,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            Button(
                onClick = {
                    Log.d("ozlem was here", "floating action button pressed")
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            NotesList(
                cardModel = cardModel,
                modifier = modifier
            )
        }
    )
}


@Preview(name = "LightMode", showSystemUi = true)
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewNotesScreen() {
    NoteAppTheme {
        val model = CardModel(
            title = "Title",
            description = "Description",
        )
        NotesScreen(
            cardModel = model,
            modifier = Modifier
        )
    }
}