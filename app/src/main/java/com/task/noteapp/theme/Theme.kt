package com.task.noteapp.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    surface = CardBackgroundColorDark,
    onSurface = CardTitleColorDark,
    onPrimary = CardDescriptionColorDark,

)

private val LightColorScheme = lightColorScheme(
    surface = CardBackgroundColorLight,
    onSurface = CardTitleColorLight,
    onPrimary = CardDescriptionColorLight,
)

@Composable
fun NoteAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        if (darkTheme) {
            systemUiController.setStatusBarColor(
                color = Color.Black
            )
        } else {
            systemUiController.setStatusBarColor(
                color = Color.White
            )
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}