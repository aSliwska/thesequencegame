package com.example.thesequencegame.ui.select_game_mode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thesequencegame.ui.common_components.SequenceDialog
import com.example.thesequencegame.ui.common_components.SequenceDropDownMenu
import com.example.thesequencegame.ui.common_components.SequenceTitle
import com.example.thesequencegame.ui.game.GameMode

@Composable
fun ModeSettingsDialog(
    viewModel: SelectGameModeViewModel,
    mode: GameMode,
    onDismissRequest: () -> Unit
) {
    SequenceDialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(24.dp)
        ) {
            SequenceTitle(
                text = "${mode.titleName} Settings",
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Divider(color = MaterialTheme.colorScheme.primary)

            if (viewModel.settings[mode] != null) {
                for (setting in viewModel.settings[mode]!!) {
                    SettingRow(setting)
                }
            }
        }
    }
}

@Composable
private fun SettingRow(
    setting: ModeSetting,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
    ) {
        Text(
            text = setting.description,
            style = MaterialTheme.typography.bodyMedium,
        )

        SequenceDropDownMenu(
            chosenOption = setting.currentChosenIndex,
            options = setting.options
        )
    }
}