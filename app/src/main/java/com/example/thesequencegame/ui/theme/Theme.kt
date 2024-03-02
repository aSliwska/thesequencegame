package com.example.thesequencegame.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.example.thesequencegame.ui.themes.ThemesViewModel

val LightBrownColorScheme = lightColorScheme(
    background = CremeColor,
    onBackground = DarkBrownColor,
    primary = LightBrownColor,
    onPrimary = DarkBrownColor,
    inversePrimary = LightBrownFlashColor,
    secondary = LightGreenColor,
    onSecondary = GreenColor,
    tertiary = LightRedColor,
    onTertiary = RedColor,
    surface = ChocolateBrownColor,
    inverseSurface = BrownColor,
    onSurface = CremeColor,
)

val DarkPurpleColorScheme = darkColorScheme(
    background = DarkPurpleColor,
    onBackground = LightWhitePurpleColor,
    primary = LightPurpleColor,
    onPrimary = DarkPurpleColor,
    inversePrimary = LightWhitePurpleColor, // LightPurpleFlashColor
    secondary = LightGreenColor,
    onSecondary = GreenColor,
    tertiary = LightYellowColor,
    onTertiary = DarkOrangeColor,
    surface = MutedPurpleColor,
    inverseSurface = MutedGrayPurpleColor,
    onSurface = LightWhitePurpleColor,
)

val DarkGrayColorScheme = darkColorScheme(
    background = DarkGrayColor,
    onBackground = LightWhiteGrayColor,
    primary = LightGrayColor,
    onPrimary = DarkGrayColor,
    inversePrimary = LightWhiteGrayColor, // LightGrayFlashColor
    secondary = LightGreenColor,
    onSecondary = GreenColor,
    tertiary = LightRedColor,
    onTertiary = RedColor,
    surface = GrayColor,
    inverseSurface = MutedGrayColor,
    onSurface = LightWhiteGrayColor,
)

@Composable
fun TheSequenceGameTheme(
    content: @Composable () -> Unit
) {
    val viewModel = remember { ThemesViewModel }
    val theme = viewModel.currentTheme.collectAsState()

    MaterialTheme(
        colorScheme = theme.value.colorScheme,
        typography = SequenceTypography,
        content = content
    )
}