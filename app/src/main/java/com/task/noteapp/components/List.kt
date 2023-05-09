package com.task.noteapp.components

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.task.noteapp.data.model.Note
import com.task.noteapp.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun List(
    note: List<Note>,
    modifier: Modifier,
) {
    LazyVerticalStaggeredGrid(
        contentPadding = PaddingValues(ListContentPaddingValues),
        verticalItemSpacing = ListVerticalItemSpacing,
        horizontalArrangement = Arrangement.spacedBy(ListHorizontalArrangementSpacing),
        columns = StaggeredGridCells.Fixed(ListItemCountPerColumn),
        content = {
            items(note) {
                NoteCard(
                    note = it,
                    modifier = modifier
                )
            }
        }
    )
}

@Composable
fun NotesList(
    note: List<Note>,
    modifier: Modifier,
) {
    List(
        note = note,
        modifier = modifier
    )
}

@Preview(name = "LightMode", showSystemUi = true)
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewNotesList() {
    NoteAppTheme {
        val model = Note(
            title = "Title",
            description = "Description",
        )
        /*NotesList(
            modifier = Modifier,
            note = model
        )*/
    }
}