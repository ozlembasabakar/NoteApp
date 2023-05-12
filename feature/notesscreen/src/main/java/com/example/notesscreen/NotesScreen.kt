package com.example.notesscreen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.components.NotesList
import com.example.model.model.Note
import com.example.components.Button

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesScreen(
    note: List<Note>,
    onAddNoteClick: () -> Unit,
    onDeleteNoteClick: (Note) -> Unit,
    onOpenNotesDetailClick: (Note) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            Button(
                onClick = {
                    onAddNoteClick()
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            NotesList(
                note = note,
                onDeleteNoteClick = onDeleteNoteClick,
                onOpenNotesDetailClick = onOpenNotesDetailClick
            )
        }
    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@Preview(name = "LightMode", showSystemUi = true)
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewNotesScreen() {
    com.example.theme.NoteAppTheme {

        val model = listOf(
            Note(
                id = 1,
                title = "Todo list1",
                description = "Laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX_myk5jX77-Ljy3cvcOWEVAcS_QuVL3DTXKYUmpz8hYCnzWj7j6tbd8almtUsQSxSXe0&usqp=CAU"
            ),
            Note(
                id = 2,
                title = "Todo list2",
                description = "Laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX_myk5jX77-Ljy3cvcOWEVAcS_QuVL3DTXKYUmpz8hYCnzWj7j6tbd8almtUsQSxSXe0&usqp=CAU"
            ),
            Note(
                id = 3,
                title = "Todo list3",
                description = "Laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX_myk5jX77-Ljy3cvcOWEVAcS_QuVL3DTXKYUmpz8hYCnzWj7j6tbd8almtUsQSxSXe0&usqp=CAU"
            ),
            Note(
                id = 4,
                title = "Todo list4",
                description = "Laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX_myk5jX77-Ljy3cvcOWEVAcS_QuVL3DTXKYUmpz8hYCnzWj7j6tbd8almtUsQSxSXe0&usqp=CAU"
            ),
            Note(
                id = 5,
                title = "Todo list5",
                description = "Laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash, laundry, pay the bills, take out the trash",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQX_myk5jX77-Ljy3cvcOWEVAcS_QuVL3DTXKYUmpz8hYCnzWj7j6tbd8almtUsQSxSXe0&usqp=CAU"
            )
        )

        NotesScreen(
            note = model,
            onAddNoteClick = {},
            onDeleteNoteClick = {},
            onOpenNotesDetailClick = {}
        )
    }
}