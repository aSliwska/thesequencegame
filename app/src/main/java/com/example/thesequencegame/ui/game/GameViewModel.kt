package com.example.thesequencegame.ui.game

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

enum class ButtonState {
    NEUTRAL, CORRECT, INCORRECT, MISSED, FLASHING
}

enum class GameMode(val titleName: String) {
    CLASSIC("Classic"),
    LIGHTING_ROUND("Lighting Round"),
    HARDCORE("Hardcore")
}

class GameViewModel: ViewModel() {
    var score = MutableStateFlow(0)
    var gameHasEnded = MutableStateFlow(false)
    val rows = 3
    val columns = 3
    val upperBound = rows*columns
    private val randomGen = Random.Default
    var sequence = mutableListOf<Int>()
    var currentSequenceInputIndex by mutableIntStateOf(0)

    val buttonAppearance = mutableListOf<MutableStateFlow<ButtonState>>()
    val buttonEnabled = mutableListOf<MutableStateFlow<Boolean>>()

    init {
        for (i in 0 ..< upperBound) {
            buttonAppearance.add(MutableStateFlow(ButtonState.NEUTRAL))
            buttonEnabled.add(MutableStateFlow(true))
        }

        viewModelScope.launch {
            animateNextSequence()
        }
    }

    private suspend fun animateNextSequence(lastChangedAppearance: Int? = null) {
        addNewSequenceElement()
        launchAnimation(lastChangedAppearance)
    }

    suspend fun pressedButton(buttonID: Int) {
        // correct
        if (buttonID == sequence[currentSequenceInputIndex]) {
            currentSequenceInputIndex += 1

            // if this was the last element in the sequence
            if (currentSequenceInputIndex == sequence.size) {
                score.value += 1
                currentSequenceInputIndex = 0

                changeButtonAppearance(buttonID, ButtonState.CORRECT)

                animateNextSequence(lastChangedAppearance = buttonID)
            }

        // incorrect
        } else {
            gameHasEnded.value = true

            changeButtonAppearance(buttonID, ButtonState.INCORRECT)
            changeButtonAppearance(sequence[currentSequenceInputIndex], ButtonState.MISSED)
            lockButtons()
        }
    }

    private suspend fun launchAnimation(lastChangedAppearance: Int? = null) {
        lockButtons()

        delay(1000)

        if (lastChangedAppearance != null) {
            changeButtonAppearance(lastChangedAppearance, ButtonState.NEUTRAL)
            delay(500)
        }

        // play animation
        for (buttonID in sequence) {
            changeButtonAppearance(buttonID, ButtonState.FLASHING)
            delay(250)
            changeButtonAppearance(buttonID, ButtonState.NEUTRAL)
            delay(500)
        }

        unlockButtons()
    }

    private fun lockButtons() {
        for (i in 0 ..< upperBound) {
            buttonEnabled[i].value = false
        }
    }

    private fun unlockButtons() {
        for (i in 0 ..< upperBound) {
            buttonEnabled[i].value = true
        }
    }

    private fun addNewSequenceElement() {
        sequence.add(randomGen.nextInt(upperBound))
        Log.e("sequencetest", sequence.toString()) // TODO delete
    }

    private fun changeButtonAppearance(buttonID: Int, state: ButtonState) {
        buttonAppearance[buttonID].value = state
    }

    suspend fun resetGame() {
        score.value = 0
        gameHasEnded.value = false
        sequence.clear()
        currentSequenceInputIndex = 0

        for (i in 0 ..< upperBound) {
            changeButtonAppearance(i, ButtonState.NEUTRAL)
        }

        animateNextSequence()
    }
}
