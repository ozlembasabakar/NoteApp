package com.task.noteapp.components

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.task.noteapp.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun List(
    cardModel: CardModel,
    modifier: Modifier,
) {
    LazyVerticalStaggeredGrid(
        contentPadding = PaddingValues(ListContentPaddingValues),
        verticalItemSpacing = ListVerticalItemSpacing,
        horizontalArrangement = Arrangement.spacedBy(ListHorizontalArrangementSpacing),
        columns = StaggeredGridCells.Fixed(ListItemCountPerColumn),
        content = {
            items(15) {
                NoteCard(
                    cardModel = cardModel,
                    modifier = modifier
                )
            }
        }
    )
}

@Composable
fun NotesList(
    cardModel: CardModel,
    modifier: Modifier,
) {
    List(
        cardModel = cardModel,
        modifier = modifier
    )
}

@Preview(name = "LightMode", showSystemUi = true)
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewNotesList() {
    NoteAppTheme {
        val model = CardModel(
            title = "Title",
            description = "Description",
        )
        NotesList(
            modifier = Modifier,
            cardModel = model
        )
    }
}