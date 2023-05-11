package com.example.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.model.model.Note

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun List(
    note: List<Note>,
    onDeleteNoteClick: (Note) -> Unit,
    onOpenNotesDetailClick: (Note) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalStaggeredGrid(
        contentPadding = PaddingValues(com.example.theme.ListContentPaddingValues),
        verticalItemSpacing = com.example.theme.ListVerticalItemSpacing,
        horizontalArrangement = Arrangement.spacedBy(com.example.theme.ListHorizontalArrangementSpacing),
        columns = StaggeredGridCells.Fixed(com.example.theme.ListItemCountPerColumn),
        content = {
            items(note) { noteList ->
                NoteCard(
                    note = noteList,
                    onDeleteNoteClick = { note ->
                        onDeleteNoteClick(note)
                    },
                    onOpenNotesDetailClick = { note ->
                        onOpenNotesDetailClick(note)
                    }
                )
            }
        },
        modifier = modifier
            .fillMaxSize()
            .testTag("List")
    )
}

@Composable
fun NotesList(
    note: List<Note>,
    onDeleteNoteClick: (Note) -> Unit,
    onOpenNotesDetailClick: (Note) -> Unit,
) {
    List(
        note = note,
        onDeleteNoteClick = onDeleteNoteClick,
        onOpenNotesDetailClick = onOpenNotesDetailClick
    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@Preview(name = "LightMode", showSystemUi = true)
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewNotesList() {
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

        NotesList(
            note = model,
            onDeleteNoteClick = {},
            onOpenNotesDetailClick = {}
        )
    }
}