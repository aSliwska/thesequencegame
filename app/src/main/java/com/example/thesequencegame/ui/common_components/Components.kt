package com.example.thesequencegame.ui.common_components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.destinations.HighScoresScreenDestination
import com.example.thesequencegame.ui.destinations.SelectGameModeScreenDestination
import com.example.thesequencegame.ui.destinations.SettingsScreenDestination
import com.example.thesequencegame.ui.destinations.ThemesScreenDestination
import com.example.thesequencegame.ui.game.GameBoard
import com.example.thesequencegame.ui.theme.SequenceTypography
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Composable
fun SequenceNavigationDrawer(
    navigator: DestinationsNavigator,
    content: @Composable (extendDrawer: () -> Unit) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp),
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Column {
                    GameBoard(
                        buttonSize = 4.dp,
                        spacerSize = 1.dp,
                    )

                    Divider(
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(8.dp),
                    )

                    Surface(
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        Column {
                            DrawerMenuButton(
                                text = "New game",
                                icon = R.drawable.play_filled,
                                onClick = {
                                    navigator.navigate(SelectGameModeScreenDestination())
                                }
                            )

                            DrawerMenuButton(
                                text = "High scores",
                                icon = R.drawable.leaderboard_filled,
                                onClick = {
                                    navigator.navigate(HighScoresScreenDestination())
                                }
                            )

                            DrawerMenuButton(
                                text = "Themes",
                                icon = R.drawable.palette_filled,
                                onClick = {
                                    navigator.navigate(ThemesScreenDestination())
                                }
                            )

                            DrawerMenuButton(
                                text = "Settings",
                                icon = R.drawable.settings_filled,
                                onClick = {
                                    navigator.navigate(SettingsScreenDestination())
                                }
                            )
                        }
                    }


                }
            }
        },
    ) {
        content(
            extendDrawer = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }
        )
    }
}

@Composable
private fun DrawerMenuButton(
    text: String,
    icon: Int,
    onClick: () -> Unit
) {
    SequenceButton(
        text = text,
        textColor = MaterialTheme.colorScheme.background,
        backgroundColor = Color.Transparent,
        icon = icon,
        contentDescription = text,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = onClick
    )
}

@Composable
private fun MiniSequenceGame() {

}

@Composable
fun SequenceTitle(text: String) {
    Text(
        text = text,
        style = SequenceTypography.titleLarge
    )
}

@Composable
fun SequenceTopBar(
    leftIcon: @Composable () -> Unit,
    rightIcon: @Composable () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 32.dp,
                start = 16.dp,
                end = 16.dp,
            )
    ) {
        leftIcon()
        rightIcon()
    }
}

@Composable
fun SequenceButton(
    text: String = "",
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    icon: Int? = null,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val mutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed = mutableInteractionSource.collectIsPressedAsState()
    val animatedScale = animateFloatAsState(if (isPressed.value) 0.95f else 1f, label = "animatedScale")
    val animatedAlpha = animateFloatAsState(if (isPressed.value) 0.8f else 1f, label = "animatedAlpha")

    Surface(
        color = backgroundColor,
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .scale(animatedScale.value)
            .alpha(animatedAlpha.value)
            .clickable(interactionSource = mutableInteractionSource, indication = null) {
                onClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(icon),
                    tint = textColor,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(48.dp)
                )
            }
            Text(
                text = text,
                color = textColor,
                style = SequenceTypography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 24.dp)
            )
        }
    }
}

@Composable
fun SequenceIconButton(
    icon: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    Surface(
        color = Color.Transparent,
        onClick = onClick,
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier.size(56.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun SequenceIconButtonWithLabel(
    icon: Int,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SequenceIconButton(
            icon = icon,
            contentDescription = label,
            onClick = onClick
        )

        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall
        )
    }
}