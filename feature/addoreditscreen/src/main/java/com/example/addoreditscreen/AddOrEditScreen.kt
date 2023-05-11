package com.example.addoreditscreen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.icon.AppIcons
import com.example.theme.*
import com.task.noteapp.data.model.AddOrEditModel

@Composable
fun AddOrEditScreen(
    addOrEditModel: AddOrEditModel,
    addNoteEvent: () -> Unit,
    modifier: Modifier,
) {

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = AddOrEditScreenColumnVerticalPadding)
            .testTag("AddOrEditScreen"),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(AddOrEditScreenColumnVerticalPadding)
    ) {
        Icon(
            painter = painterResource(id = AppIcons.BackIcon),
            contentDescription = AppIcons.BackIcon.toString(),
            modifier = Modifier
                .padding(start = AddOrEditScreenIconPadding)
                .clip(Shapes.small)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = {
                        addNoteEvent()
                    }
                )
                .testTag("BackButton"),
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        com.example.components.TextField(
            value = addOrEditModel.title,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("Title"),
            textStyle = MaterialTheme.typography.headlineSmall,
            placeholder = {
                Text(
                    text = stringResource(R.string.title),
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.titleSmall
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) }
            ),
            singleLine = true,
            maxLines = 1
        )

        com.example.components.TextField(
            value = addOrEditModel.imageUrl,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(R.string.imageUrl),
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.bodySmall
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Next) }
            ),
            singleLine = true,
            maxLines = 1,
        )

        com.example.components.TextField(
            value = addOrEditModel.note,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(R.string.note),
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.bodySmall
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.clearFocus() }
            ),
        )
        Text(
            text = stringResource(id = R.string.edited) + " " + addOrEditModel.date.value,
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.displaySmall,
            textAlign = TextAlign.Center
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(name = "LightMode", showSystemUi = true)
@Preview(name = "DarkMode", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAddOrEditScreen() {
    NoteAppTheme {

        val addOrEditModel = AddOrEditModel(
            title = mutableStateOf("Title"),
            imageUrl = mutableStateOf("Image Url"),
            note = mutableStateOf("Note"),
        )

        AddOrEditScreen(
            addOrEditModel = addOrEditModel,
            addNoteEvent = {},
            modifier = Modifier
        )
    }
}