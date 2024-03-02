package com.example.thesequencegame.ui.high_scores

import androidx.lifecycle.ViewModel
import com.example.thesequencegame.ui.game.GameMode
import kotlinx.coroutines.flow.MutableStateFlow

class HighScoresViewModel: ViewModel() {
    private val modeList = GameMode.entries
    val chosenModeID = MutableStateFlow(GameMode.CLASSIC.ordinal)
    val chosenModeName = MutableStateFlow(modeList[chosenModeID.value].titleName)
    val rankings = mutableListOf<MutableList<Int>>()

    init {
        for (i in 0..<modeList.size) {
            rankings.add(mutableListOf())
        }

        // TODO get actual high scores
        rankings[GameMode.CLASSIC.ordinal].add(56)
        rankings[GameMode.CLASSIC.ordinal].add(37)
        rankings[GameMode.CLASSIC.ordinal].add(34)
        rankings[GameMode.CLASSIC.ordinal].add(21)
        rankings[GameMode.CLASSIC.ordinal].add(2)
        rankings[GameMode.CLASSIC.ordinal].add(1)
        rankings[GameMode.CLASSIC.ordinal].add(1)
        rankings[GameMode.CLASSIC.ordinal].add(1)
        rankings[GameMode.CLASSIC.ordinal].add(1)
        rankings[GameMode.CLASSIC.ordinal].add(1)
        rankings[GameMode.CLASSIC.ordinal].add(1)
        rankings[GameMode.CLASSIC.ordinal].add(1)
        rankings[GameMode.LIGHTING_ROUND.ordinal].add(543)
    }
    fun cycleModeRight() {
        chosenModeID.value = (chosenModeID.value + 1) % modeList.size
        chosenModeName.value = modeList[chosenModeID.value].titleName
    }

    fun cycleModeLeft() {
        chosenModeID.value = if (chosenModeID.value > 0) chosenModeID.value - 1 else modeList.size - 1
        chosenModeName.value = modeList[chosenModeID.value].titleName
    }
}