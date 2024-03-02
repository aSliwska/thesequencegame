package com.example.thesequencegame.ui.settings

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SettingsViewModel: ViewModel() {
    var volume = MutableStateFlow(10.0f)
}