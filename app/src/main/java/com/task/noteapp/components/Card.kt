package com.task.noteapp.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.task.noteapp.R
import com.task.noteapp.data.model.Note
import com.task.noteapp.theme.*

@Composable
fun Card(
    note: Note,
    onDeleteNoteClick: (Note) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = Shapes.small)
            .border(
                width = CardColumnBorderSize,
                color = CardColumnBorderColor,
                shape = Shapes.small
            )
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.End
    ) {
        AsyncImage(
            model = note.imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = Shapes.small),
            contentDescription = note.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.placeholder)
        )
        Column(
            modifier = Modifier
                .padding(CardTextColumnPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = note.description,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodySmall,
                maxLines = CardDescriptionTextMaxLineCount
            )
        }
        Icon(
            Icons.Default.Delete,
            contentDescription = Icons.Filled.Delete.name,
            modifier = Modifier
                .padding(CardIconPadding)
                .clickable(
                    onClick = {
                        onDeleteNoteClick(note)
                    }
                )
        )
    }
}

@Composable
fun NoteCard(
    note: Note,
    onDeleteNoteClick: (Note) -> Unit,
) {
    Card(note = note, onDeleteNoteClick = onDeleteNoteClick)
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCard() {
    NoteAppTheme {
        val note = Note(
            title = "Title",
            description = "Description",
        )
        Card(
            note = note,
            onDeleteNoteClick = {}
        )
    }
}