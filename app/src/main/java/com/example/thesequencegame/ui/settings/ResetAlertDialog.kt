package com.example.thesequencegame.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.common_components.SequenceButton
import com.example.thesequencegame.ui.common_components.SequenceDialog
import com.example.thesequencegame.ui.common_components.SequenceTitle

@Composable
fun ResetAlertDialog(
    onDismissRequest: () -> Unit,
) {
    SequenceDialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.warning),
                contentDescription = "warning",
                modifier = Modifier.size(48.dp)
            )

            Box(
                modifier = Modifier.padding(bottom = 32.dp).fillMaxWidth(),
            ) {
                SequenceTitle(
                    text = "This action is irreversible!",
                    color = MaterialTheme.colorScheme.tertiary,
                    drawStyle = Stroke(width = 24f, join = StrokeJoin.Round),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )

                SequenceTitle(
                    text = "This action is irreversible!",
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            }


            Text(
                text = "Really reset?",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                SequenceButton(
                    text = "Yes",
                    textColor = MaterialTheme.colorScheme.onTertiary,
                    backgroundColor = MaterialTheme.colorScheme.tertiary,
                    textModifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    /*TODO*/
                }

                SequenceButton(
                    text = "No",
                    textModifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)
                ) {
                    onDismissRequest()
                }
            }

        }
    }
}