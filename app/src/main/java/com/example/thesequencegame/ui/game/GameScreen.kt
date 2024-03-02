package com.example.thesequencegame.ui.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.common_components.SequenceButton
import com.example.thesequencegame.ui.common_components.SequenceIconButton
import com.example.thesequencegame.ui.common_components.SequenceIconButtonWithLabel
import com.example.thesequencegame.ui.common_components.SequenceTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun GameScreen(
    navigator: DestinationsNavigator,
    viewModel: GameViewModel = viewModel(),
) {
    val score by viewModel.score.collectAsState()
    val gameHasEnded by viewModel.gameHasEnded.collectAsState()
    val animatedVisibility by animateFloatAsState(if (gameHasEnded) 1.0f else 0.0f, label = "end game elements' visibility")

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
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Spacer(
                Modifier
                    .height(0.dp)
                    .weight(0.1f)
            )

            Text(
                text = "Score: $score",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(0.15f)
            )

            GameBoard(
                viewModel = viewModel,
                buttonSize = 88.dp,
                spacerSize = 8.dp,
                modifier = Modifier.weight(0.45f)
            )

            AnimatedVisibility(
                visible = gameHasEnded,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "GAME OVER",
                        style = MaterialTheme.typography.titleLarge,
                    )

                    Spacer(Modifier.height(64.dp))

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
                            viewModel.viewModelScope.launch {
                                viewModel.resetGame()
                            }
                        }
                    }
                }

            }
            Spacer(
                Modifier
                    .height(0.dp)
                    .weight(0.2f)
            )
        }
    }
}

@Composable
private fun GameBoard(
    viewModel: GameViewModel,
    buttonSize: Dp,
    spacerSize: Dp,
    buttonShape: Shape = MaterialTheme.shapes.large,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        GameRow(viewModel, buttonSize, spacerSize, buttonShape, (0 ..< viewModel.columns).toList())

        for (row in 1 ..< viewModel.rows) {
            Spacer(modifier = Modifier.height(spacerSize))
            GameRow(viewModel, buttonSize, spacerSize, buttonShape, (row*viewModel.columns ..< (row+1)*viewModel.columns).toList())
        }
    }
}

@Composable
private fun GameRow(
    viewModel: GameViewModel,
    buttonSize: Dp,
    spacerSize: Dp,
    buttonShape: Shape,
    buttonIDs: List<Int>
) {
    Row(
        horizontalArrangement = Arrangement.Center,
    ) {
        GameButton(buttonIDs[0], viewModel, buttonShape, Modifier.size(buttonSize))

        for (col in 1 ..< viewModel.columns) {
            Spacer(modifier = Modifier.width(spacerSize))
            GameButton(buttonIDs[col], viewModel, buttonShape, Modifier.size(buttonSize))
        }
    }
}

@Composable
private fun GameButton(
    buttonID: Int,
    viewModel: GameViewModel,
    buttonShape: Shape,
    modifier: Modifier = Modifier,
) {
    val buttonState by viewModel.buttonAppearance[buttonID].collectAsState()
    val animatedColor by animateColorAsState(
        when (buttonState) {
            ButtonState.CORRECT -> MaterialTheme.colorScheme.secondary
            ButtonState.INCORRECT -> MaterialTheme.colorScheme.tertiary
            ButtonState.FLASHING -> MaterialTheme.colorScheme.inversePrimary
            else -> MaterialTheme.colorScheme.primary
        },
        label = "button color"
    )
    val icon = (
        when (buttonState) {
            ButtonState.MISSED -> R.drawable.check_empty
            ButtonState.CORRECT -> R.drawable.check
            ButtonState.INCORRECT -> R.drawable.cross
            else -> null
        }
    )?.let {
        animateIntAsState(
            it, label = "icon"
        )
    }
    val iconColor by animateColorAsState(
        when (buttonState) {
            ButtonState.MISSED -> MaterialTheme.colorScheme.background
            ButtonState.CORRECT -> MaterialTheme.colorScheme.onSecondary
            ButtonState.INCORRECT -> MaterialTheme.colorScheme.onTertiary
            else -> MaterialTheme.colorScheme.primary
        },
        label = "icon color"
    )
    val enabled by viewModel.buttonEnabled[buttonID].collectAsState()

    SequenceButton(
        shape = buttonShape,
        backgroundColor = animatedColor,
        icon = icon?.value,
        textColor = iconColor,
        enabled = enabled,
        modifier = modifier,
    ) {
        viewModel.viewModelScope.launch {
            viewModel.pressedButton(buttonID)
        }
    }
}