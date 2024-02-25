package com.example.thesequencegame.ui.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
    var score by mutableStateOf(0)
    var gameHasEnded by mutableStateOf(false)


}