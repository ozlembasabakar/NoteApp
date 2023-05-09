package com.task.noteapp.addoreditscreen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.task.noteapp.R
import com.task.noteapp.Screen
import com.task.noteapp.components.TextField
import com.task.noteapp.theme.AddOrEditScreenColumnVerticalPadding
import com.task.noteapp.theme.AddOrEditScreenIconPadding
import com.task.noteapp.theme.NoteAppTheme
import com.task.noteapp.theme.Shapes

@Composable
fun AddOrEditScreen(
    navController: NavController,
    modifier: Modifier,
) {

    val focusManager = LocalFocusManager.current

    var title = remember {
        mutableStateOf("")
    }
    var imageUrl = remember {
        mutableStateOf("")
    }
    var note = remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = AddOrEditScreenColumnVerticalPadding),
        horizontalAlignment = Alignment.Start
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back_icon),
            contentDescription = R.drawable.back_icon.toString(),
            modifier = Modifier
                .padding(start = AddOrEditScreenIconPadding)
                .clip(Shapes.small)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = true),
                    onClick = {
                        navController.navigate(Screen.NotesScreen.route)
                    }
                ),
            tint = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        TextField(
            value = title,
            modifier = Modifier.fillMaxWidth(),
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

        TextField(
            value = imageUrl,
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

        TextField(
            value = note,
            modifier = Modifier.fillMaxSize(),
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
    }
}

@Preview(name = "LightMode", showSystemUi = true)
@Preview(name = "DarkMode", showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAddOrEditScreen() {
    NoteAppTheme {
        val navController = rememberNavController()
        AddOrEditScreen(navController = navController, modifier = Modifier)
    }
}