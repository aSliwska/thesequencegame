package com.example.thesequencegame.ui.select_game_mode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.common_components.SequenceDialog
import com.example.thesequencegame.ui.common_components.SequenceTitle

@Composable
fun HowToPlayDialog(
    onDismissRequest: () -> Unit,
) {
    SequenceDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp)
        ) {
            SequenceTitle(
                text = "HOW TO PLAY",
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Divider(color = MaterialTheme.colorScheme.primary)

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                item {
                    TitleRow(
                        icon = R.drawable.star,
                        text = "Classic",
                    )
                }
                item {
                    TextRow(text = "Watch the buttons light up and remember the pattern. " +
                            "When they stop, press them in the same order. " +
                            "The sequence gets longer every round."
                    )
                }
                item {
                    TitleRow(
                        icon = R.drawable.bolt,
                        text = "Lighting Round",
                    )
                }
                item {
                    TextRow(text = "Same as Classic but only a few most recent buttons " +
                            "in the sequence light up. You still need to remember and input " +
                            "the whole pattern."
                    )
                }
                item {
                    TitleRow(
                        icon = R.drawable.skull,
                        text = "Hardcore",
                    )
                }
                item {
                    TextRow(text = "The number of buttons that light up gets smaller with " +
                            "each round. As always, you still need to remember and input " +
                            "the entire pattern."
                    )
                }
            }
        }
    }
}

@Composable
private fun TitleRow(
    icon: Int,
    text: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 12.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(32.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun TextRow(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Justify,
        modifier = Modifier.padding(bottom = 24.dp)
    )
}