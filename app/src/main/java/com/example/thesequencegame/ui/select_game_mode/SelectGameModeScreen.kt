package com.example.thesequencegame.ui.select_game_mode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.common_components.SequenceButton
import com.example.thesequencegame.ui.common_components.SequenceIconButton
import com.example.thesequencegame.ui.common_components.SequenceNavigationDrawer
import com.example.thesequencegame.ui.common_components.SequenceTitle
import com.example.thesequencegame.ui.common_components.SequenceTopBar
import com.example.thesequencegame.ui.destinations.GameScreenDestination
import com.example.thesequencegame.ui.game.GameMode
import com.example.thesequencegame.ui.theme.SequenceTypography
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun SelectGameModeScreen(
    navigator: DestinationsNavigator,
    viewModel: SelectGameModeViewModel = viewModel(),
) {
    var showHowToPlayDialog by remember { mutableStateOf(false) }
    var showClassicSettingsDialog by remember { mutableStateOf(false) }

    SequenceNavigationDrawer(navigator) { extendDrawer ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            SequenceTopBar(
                leftIcon = {
                    SequenceIconButton(
                        icon = R.drawable.menu,
                        contentDescription = "menu",
                        onClick = extendDrawer
                    )
                },
                rightIcon = {
                    SequenceIconButton(
                        icon = R.drawable.question,
                        contentDescription = "how to play"
                    ) {
                        showHowToPlayDialog = true
                    }
                }
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(
                    Modifier
                        .height(0.dp)
                        .weight(0.1f)
                )

                SequenceTitle(text = "NEW GAME", modifier = Modifier.weight(0.15f))

                GameModeCard(
                    name = "Classic",
                    description = "The whole sequence is always shown.",
                    onClick = {
                        navigator.navigate(GameScreenDestination())
                    },
                    onSettingsClick = {
                        showClassicSettingsDialog = true
                    },
                    modifier = Modifier.weight(0.15f)
                )

                GameModeCard(
                    name = "Lighting Round",
                    description = "Only the end of the sequence is shown.",
                    onClick = { /* TODO */ },
                    onSettingsClick = { /* TODO */ },
                    modifier = Modifier.weight(0.15f)
                )

                GameModeCard(
                    name = "Hardcore",
                    description = "The length of the visible part of the sequence decreases.",
                    onClick = { /* TODO */ },
                    onSettingsClick = { /* TODO */ },
                    modifier = Modifier.weight(0.4f)
                )
            }
        }
    }

    if (showHowToPlayDialog) {
        HowToPlayDialog {
            showHowToPlayDialog = false
        }
    }
    if (showClassicSettingsDialog) {
        ModeSettingsDialog(
            viewModel = viewModel,
            mode = GameMode.CLASSIC
        ) {
            showClassicSettingsDialog = false
        }
    }
}

@Composable
private fun GameModeCard(
    name: String,
    description: String,
    onClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            SequenceButton(
                text = name,
                arrangement = Arrangement.Start,
                onClick = onClick,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            SequenceIconButton(
                icon = R.drawable.settings_filled,
                contentDescription = "mode settings",
                onClick = onSettingsClick,
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = description,
            style = SequenceTypography.labelSmall,
        )
    }
}
