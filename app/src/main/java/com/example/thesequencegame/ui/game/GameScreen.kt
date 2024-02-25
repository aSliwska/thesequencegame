package com.example.thesequencegame.ui.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.common_components.SequenceButton
import com.example.thesequencegame.ui.common_components.SequenceIconButton
import com.example.thesequencegame.ui.common_components.SequenceIconButtonWithLabel
import com.example.thesequencegame.ui.common_components.SequenceTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.lifecycle.viewmodel.compose.viewModel

@Destination
@Composable
fun GameScreen(
    navigator: DestinationsNavigator,
    viewModel: GameViewModel = viewModel(),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        SequenceTopBar(
            leftIcon = {
                SequenceIconButton(
                    icon = R.drawable.arrow_back,
                    contentDescription = "menu",
                    onClick = { navigator.navigateUp() }
                )
            }
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                text = "Score: ",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(32.dp))

            GameBoard(
                buttonSize = 88.dp,
                spacerSize = 8.dp,
                modifier = Modifier.weight(0.5f)
            )

            if (viewModel.gameHasEnded) {
                Text(
                    text = "GAME OVER",
                    style = MaterialTheme.typography.titleLarge,
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    SequenceIconButtonWithLabel(
                        icon = R.drawable.share,
                        label = "Share a GIF"
                    ) {
                        /* TODO */
                    }

                    Spacer(modifier = Modifier.width(64.dp))

                    SequenceIconButtonWithLabel(
                        icon = R.drawable.replay,
                        label = "Play again"
                    ) {
                        /* TODO */
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(56.dp))
    }

}

@Composable
fun GameBoard(
    buttonSize: Dp,
    spacerSize: Dp,
    modifier: Modifier = Modifier,
) {
    Column {
        GameRow(buttonSize, spacerSize)
        Spacer(modifier = Modifier.height(spacerSize))
        GameRow(buttonSize, spacerSize)
        Spacer(modifier = Modifier.height(spacerSize))
        GameRow(buttonSize, spacerSize)
    }
}

@Composable
private fun GameRow(
    buttonSize: Dp,
    spacerSize: Dp,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
    ) {
        GameButton(Modifier.size(buttonSize))
        Spacer(modifier = Modifier.width(spacerSize))
        GameButton(Modifier.size(buttonSize))
        Spacer(modifier = Modifier.width(spacerSize))
        GameButton(Modifier.size(buttonSize))
    }
}

@Composable
private fun GameButton(
    modifier: Modifier = Modifier,
) {
    SequenceButton(
        modifier = modifier,
    ) {
        /* TODO */
    }
}