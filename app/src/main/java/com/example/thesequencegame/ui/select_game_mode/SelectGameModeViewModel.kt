package com.example.thesequencegame.ui.select_game_mode

import androidx.lifecycle.ViewModel
import com.example.thesequencegame.ui.game.GameMode

class ModeSetting(
    val description: String,
    val options: Map<Int, String>,
    val currentChosenIndex: Int,
) {}
class SelectGameModeViewModel: ViewModel() {
    val settings = mapOf<GameMode, List<ModeSetting>>(
        GameMode.CLASSIC to listOf(
            ModeSetting(
                description = "Speed",
                options = mapOf(1 to "1", 2 to "2", 3 to "3"),
                currentChosenIndex = 3
            ),
            ModeSetting(
                description = "Grid size",
                options = mapOf(2 to "2x2", 3 to "3x3", 4 to "4x4"),
                currentChosenIndex = 3
            ),
        ),

    )
}