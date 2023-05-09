package com.task.noteapp.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.task.noteapp.theme.NoteAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    value: MutableState<String>,
    modifier: Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    placeholder: @Composable (() -> Unit),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
) {
    TextField(
        value = value.value,
        onValueChange = {
            value.value = it
        },
        modifier = modifier,
        textStyle = textStyle,
        placeholder = {
            placeholder()
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            cursorColor = MaterialTheme.colorScheme.onSurface,
            selectionColors = TextSelectionColors(
                backgroundColor = MaterialTheme.colorScheme.onTertiaryContainer,
                handleColor = MaterialTheme.colorScheme.onTertiaryContainer
            )
        )
    )
}

@Preview(name = "LightMode")
@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTextField() {
    NoteAppTheme {
        val value = remember {
            mutableStateOf("")
        }
        TextField(
            value = value,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Value",
                    color = MaterialTheme.colorScheme.onTertiary,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        )
    }
}