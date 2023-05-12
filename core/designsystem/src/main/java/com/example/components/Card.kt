package com.example.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.icon.AppIcons
import com.example.theme.*
import com.example.model.model.Note

@Composable
fun Card(
    note: Note,
    onDeleteNoteClick: (Note) -> Unit,
    onOpenNotesDetailClick: (Note) -> Unit,
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
            .background(MaterialTheme.colorScheme.surface)
            .clickable(
                onClick = {
                    onOpenNotesDetailClick(note)
                }
            )
            .testTag("Card"),
        horizontalAlignment = Alignment.End
    ) {
        AsyncImage(
            model = note.imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = Shapes.small),
            contentDescription = note.title,
            contentScale = ContentScale.Crop,
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CardIconPadding)
        ) {
            Text(
                text = note.date,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displaySmall,
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = AppIcons.TrashIcon),
                contentDescription = Icons.Filled.Delete.name,
                modifier = Modifier
                    .clip(Shapes.small)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = true),
                        onClick = {
                            onDeleteNoteClick(note)
                        }
                    )
                    .testTag("Delete")
            )
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    onDeleteNoteClick: (Note) -> Unit,
    onOpenNotesDetailClick: (Note) -> Unit,
) {
    Card(
        note = note,
        onDeleteNoteClick = onDeleteNoteClick,
        onOpenNotesDetailClick = onOpenNotesDetailClick
    )
}

@SuppressLint("UnrememberedMutableState")
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
            onDeleteNoteClick = {},
            onOpenNotesDetailClick = {}
        )
    }
}