package com.task.noteapp.components

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
import com.task.noteapp.RemoteDatasource
import com.task.noteapp.data.model.Note
import com.task.noteapp.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun List(
    note: List<Note>,
    onDeleteNoteClick: (Note) -> Unit,
    onOpenNotesDetailClick: (Note) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalStaggeredGrid(
        contentPadding = PaddingValues(ListContentPaddingValues),
        verticalItemSpacing = ListVerticalItemSpacing,
        horizontalArrangement = Arrangement.spacedBy(ListHorizontalArrangementSpacing),
        columns = StaggeredGridCells.Fixed(ListItemCountPerColumn),
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
    NoteAppTheme {

        val model = RemoteDatasource().datasource.value

        NotesList(
            note = model,
            onDeleteNoteClick = {},
            onOpenNotesDetailClick = {}
        )
    }
}