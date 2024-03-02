package com.example.thesequencegame.ui.high_scores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.common_components.SequenceIconButton
import com.example.thesequencegame.ui.common_components.SequenceNavigationDrawer
import com.example.thesequencegame.ui.common_components.SequenceTitle
import com.example.thesequencegame.ui.common_components.SequenceTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun HighScoresScreen(
    navigator: DestinationsNavigator,
    viewModel: HighScoresViewModel = viewModel(),
) {
    val chosenModeID by viewModel.chosenModeID.collectAsState()

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
                }
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize()
            ) {
                SequenceTitle(text = "HIGH SCORES",)

                Divider(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 16.dp)
                )

                ModeChoiceRow(
                    viewModel = viewModel,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                RankingHeader()

                if (viewModel.rankings[chosenModeID].size > 0) {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                    ) {
                        itemsIndexed(viewModel.rankings[chosenModeID]) { index, score ->
                            HighScore(
                                place = index+1,
                                score = score,
                            )
                        }
                    }
                } else {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "No records (yet)",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ModeChoiceRow(
    viewModel: HighScoresViewModel,
    modifier: Modifier = Modifier
) {
    val chosenModeName = viewModel.chosenModeName.collectAsState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth(),
    ) {
        SequenceIconButton(
            icon = R.drawable.arrow_left,
            contentDescription = "previous mode"
        ) {
            viewModel.cycleModeLeft()
        }

        Text(
            text = chosenModeName.value,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )

        SequenceIconButton(
            icon = R.drawable.arrow_right,
            contentDescription = "next mode"
        ) {
            viewModel.cycleModeRight()
        }
    }
}

@Composable
private fun RankingHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Place",
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(56.dp)
        )

        Text(
            text = "Score",
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(56.dp)
        )

        Spacer(modifier = Modifier.width(56.dp))
    }

    Divider(color = MaterialTheme.colorScheme.onBackground)
}

@Composable
private fun HighScore(
    place: Int,
    score: Int,
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = "$place.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(56.dp)
            )

            Text(
                text = "$score",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
            )
            
            if (place <= 3) {
                SequenceIconButton(
                    icon = R.drawable.share,
                    contentDescription = "share a gif",
                ) {
                    /*TODO*/
                }
            } else {
                Spacer(modifier = Modifier.size(56.dp))
            }
        }
    }
}