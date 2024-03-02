package com.example.thesequencegame.ui.support_me

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.common_components.SequenceDialog
import com.example.thesequencegame.ui.common_components.SequenceTitle

@Composable
fun AdWatchedDialog(
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
            SequenceTitle(
                text = "Ad watched!",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Thank you so much!!",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = "heart",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}