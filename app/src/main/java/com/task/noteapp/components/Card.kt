package com.task.noteapp.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.task.noteapp.theme.*

@Composable
fun Card(
    modifier: Modifier,
    cardModel: CardModel,
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
    ) {
        AsyncImage(
            model = cardModel.imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = Shapes.small),
            contentDescription = cardModel.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.placeholder)
        )
        Column(
            modifier = Modifier.padding(CardTextColumnPadding),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = cardModel.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = cardModel.description,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodySmall,
                maxLines = CardDescriptionTextMaxLineCount
            )
        }
    }
}

@Composable
fun NoteCard(
    modifier: Modifier,
    cardModel: CardModel,
) {
    Card(cardModel = cardModel, modifier = modifier)
}

data class CardModel(
    val title: String,
    val description: String,
    val imageUrl: String = "",
)

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCard() {
    NoteAppTheme {
        val model = CardModel(
            title = "Title",
            description = "Description",
        )
        Card(
            modifier = Modifier,
            cardModel = model
        )
    }
}