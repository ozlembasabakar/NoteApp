package com.task.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import com.task.noteapp.components.CardModel
import com.task.noteapp.notesscreen.NotesScreen
import com.task.noteapp.theme.NoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                NotesScreen(
                    cardModel = CardModel(
                        title = "Title",
                        description = "Description",
                        imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX_myk5jX77-Ljy3cvcOWEVAcS_QuVL3DTXKYUmpz8hYCnzWj7j6tbd8almtUsQSxSXe0&usqp=CAU"
                    ),
                    modifier = Modifier
                )
            }
        }
    }
}