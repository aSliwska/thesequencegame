package com.example.thesequencegame.ui.select_game_mode

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.common_components.SequenceButton
import com.example.thesequencegame.ui.common_components.SequenceIconButton
import com.example.thesequencegame.ui.common_components.SequenceNavigationDrawer
import com.example.thesequencegame.ui.common_components.SequenceTitle
import com.example.thesequencegame.ui.common_components.SequenceTopBar
import com.example.thesequencegame.ui.destinations.GameScreenDestination
import com.example.thesequencegame.ui.theme.SequenceTypography
import com.example.thesequencegame.ui.theme.TheSequenceGameTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun SelectGameModeScreen(
    navigator: DestinationsNavigator
) {
    SequenceNavigationDrawer(navigator) { extendDrawer ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween, // TODO this might clip into status bar, only works by accident
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
                        /* TODO */
                    }
                }
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SequenceTitle(text = "NEW GAME")

                Spacer(Modifier.height(64.dp))

                GameModeCard(
                    name = "Classic",
                    description = "The whole sequence is always shown.",
                    onClick = {
                        navigator.navigate(GameScreenDestination())
                    },
                    onSettingsClick = { /* TODO */ },
                )

                Spacer(Modifier.height(40.dp))

                GameModeCard(
                    name = "Lighting Round",
                    description = "Only the end of the sequence is shown.",
                    onClick = { /* TODO */ },
                    onSettingsClick = { /* TODO */ },
                )

                Spacer(Modifier.height(40.dp))

                GameModeCard(
                    name = "Hardcore",
                    description = "The length of the visible part of the sequence decreases.",
                    onClick = { /* TODO */ },
                    onSettingsClick = { /* TODO */ },
                )
            }

            Spacer(modifier = Modifier.height(136.dp))
        }
    }
}

@Composable
private fun GameModeCard(
    name: String,
    description: String,
    onClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            SequenceButton(
                text = name,
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
