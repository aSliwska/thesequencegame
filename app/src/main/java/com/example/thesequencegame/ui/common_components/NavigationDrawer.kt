package com.example.thesequencegame.ui.common_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.destinations.HighScoresScreenDestination
import com.example.thesequencegame.ui.destinations.SelectGameModeScreenDestination
import com.example.thesequencegame.ui.destinations.SettingsScreenDestination
import com.example.thesequencegame.ui.destinations.SupportMeScreenDestination
import com.example.thesequencegame.ui.destinations.ThemesScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Composable
fun SequenceNavigationDrawer(
    navigator: DestinationsNavigator,
    content: @Composable (extendDrawer: () -> Unit) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val supportMeColor = MaterialTheme.colorScheme.primary

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(topEnd = 24.dp, bottomEnd = 24.dp),
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                ) {
                    MiniSequenceGame(
                        modifier = Modifier.padding(top = 56.dp, bottom = 56.dp)
                    )

                    Divider(
                        color = MaterialTheme.colorScheme.inverseSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Surface(
                        color = MaterialTheme.colorScheme.inverseSurface,
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column(
                            modifier = Modifier.padding(bottom = 8.dp)
                        ) {
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

                    Spacer(modifier = Modifier.weight(1.0f))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        SequenceButton(
                            text = "Support me :)",
                            backgroundColor = Color.Transparent,
                            textColor = supportMeColor,
                            textStyle = MaterialTheme.typography.bodySmall,
                            textModifier = Modifier.padding(16.dp),
                            modifier = Modifier.drawBehind {
                                drawLine(
                                    color = supportMeColor,
                                    strokeWidth = 1.dp.toPx(),
                                    start = Offset(40f, size.height - 14.sp.toPx()),
                                    end = Offset(size.width - 40f, size.height - 14.sp.toPx()),
                                )
                            }
                        ) {
                            navigator.navigate(SupportMeScreenDestination())
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
        textColor = MaterialTheme.colorScheme.onSurface,
        backgroundColor = Color.Transparent,
        icon = icon,
        arrangement = Arrangement.Start,
        contentDescription = text,
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth(),
        onClick = onClick
    )
}

@Composable
private fun MiniSequenceGame(
    modifier: Modifier = Modifier,
) {
    Column(modifier) { // TODO minigame
        Row(horizontalArrangement = Arrangement.Center) {
            SequenceButton(shape = MaterialTheme.shapes.small, modifier = Modifier.size(24.dp)) {}
            Spacer(modifier = Modifier.width(4.dp))
            SequenceButton(shape = MaterialTheme.shapes.small, modifier = Modifier.size(24.dp)) {}
            Spacer(modifier = Modifier.width(4.dp))
            SequenceButton(shape = MaterialTheme.shapes.small, modifier = Modifier.size(24.dp)) {}
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            SequenceButton(shape = MaterialTheme.shapes.small, modifier = Modifier.size(24.dp)) {}
            Spacer(modifier = Modifier.width(4.dp))
            SequenceButton(shape = MaterialTheme.shapes.small, modifier = Modifier.size(24.dp)) {}
            Spacer(modifier = Modifier.width(4.dp))
            SequenceButton(shape = MaterialTheme.shapes.small, modifier = Modifier.size(24.dp)) {}
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            SequenceButton(shape = MaterialTheme.shapes.small, modifier = Modifier.size(24.dp)) {}
            Spacer(modifier = Modifier.width(4.dp))
            SequenceButton(shape = MaterialTheme.shapes.small, modifier = Modifier.size(24.dp)) {}
            Spacer(modifier = Modifier.width(4.dp))
            SequenceButton(shape = MaterialTheme.shapes.small, modifier = Modifier.size(24.dp)) {}
        }
    }

//    GameBoard(
//        buttonSize = 24.dp,
//        spacerSize = 4.dp,
//        buttonShape = MaterialTheme.shapes.small,
//        modifier = modifier,
//    )
}