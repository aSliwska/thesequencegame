package com.example.thesequencegame.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
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
import com.example.thesequencegame.ui.common_components.SequenceSlider
import com.example.thesequencegame.ui.common_components.SequenceTitle
import com.example.thesequencegame.ui.common_components.SequenceTopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SettingsScreen(
    navigator: DestinationsNavigator,
    viewModel: SettingsViewModel = viewModel(),
) {

    var showResetAlertDialog by remember { mutableStateOf(false) }

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
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {
                Spacer(
                    Modifier
                        .height(0.dp)
                        .weight(0.1f)
                )

                SequenceTitle(text = "SETTINGS", modifier = Modifier.weight(0.15f))

                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier.weight(0.5f),
                ) {
                    SliderRow(
                        text = "Sound",
                        value = viewModel.volume.collectAsState(),
                    ) { newValue ->
                        viewModel.volume.value = newValue

                        /*TODO*/
                    }
                }


                Box(modifier = Modifier.weight(0.3f)) {
                    SequenceButton(
                        text = "Reset high scores",
                        textColor = MaterialTheme.colorScheme.onTertiary,
                        backgroundColor = MaterialTheme.colorScheme.tertiary,
                        rowModifier = Modifier.padding(end = 24.dp),
                    ) {
                        showResetAlertDialog = true
                    }
                }

            }
        }
    }

    if (showResetAlertDialog) {
        ResetAlertDialog {
            showResetAlertDialog = false
        }
    }
}

@Composable
private fun SliderRow(
    text: String,
    value: State<Float>,
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(end = 16.dp)
        )
        SequenceSlider(
            value = value.value,
            valueRange = 0f..10f,
            modifier = Modifier.weight(1.0f),
            onValueChange = onValueChange,
        )
    }
}