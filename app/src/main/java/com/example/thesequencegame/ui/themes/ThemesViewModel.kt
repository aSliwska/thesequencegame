package com.example.thesequencegame.ui.themes

import androidx.compose.material3.ColorScheme
import androidx.lifecycle.ViewModel
import com.example.thesequencegame.ui.game.GameMode
import com.example.thesequencegame.ui.theme.DarkGrayColorScheme
import com.example.thesequencegame.ui.theme.DarkPurpleColorScheme
import com.example.thesequencegame.ui.theme.LightBrownColorScheme
import kotlinx.coroutines.flow.MutableStateFlow

class ThemeData(
    val colorScheme: ColorScheme,
    var isActive: Boolean = false,
    var isLocked: Boolean = true,
    val unlockScore: Int? = null,
    val unlockMode: GameMode? = null,
) {}
object ThemesViewModel: ViewModel() {
    val themeDataList = listOf(
        ThemeData(LightBrownColorScheme, isActive = true, isLocked = false),
        ThemeData(DarkPurpleColorScheme, isLocked = false),
        ThemeData(DarkGrayColorScheme, isLocked = false),
        ThemeData(LightBrownColorScheme, isLocked = false),
        ThemeData(LightBrownColorScheme, unlockScore = 10, unlockMode = GameMode.CLASSIC),
        ThemeData(LightBrownColorScheme, unlockScore = 10, unlockMode = GameMode.LIGHTING_ROUND),
        ThemeData(LightBrownColorScheme, unlockScore = 10, unlockMode = GameMode.HARDCORE),
        ThemeData(LightBrownColorScheme, unlockScore = 20, unlockMode = GameMode.CLASSIC),
        ThemeData(LightBrownColorScheme, unlockScore = 20, unlockMode = GameMode.LIGHTING_ROUND),
        ThemeData(LightBrownColorScheme, unlockScore = 20, unlockMode = GameMode.HARDCORE),
        ThemeData(LightBrownColorScheme, unlockScore = 30, unlockMode = GameMode.CLASSIC),
        ThemeData(LightBrownColorScheme, unlockScore = 30, unlockMode = GameMode.LIGHTING_ROUND),
        ThemeData(LightBrownColorScheme, unlockScore = 30, unlockMode = GameMode.HARDCORE),
        ThemeData(LightBrownColorScheme, unlockScore = 50, unlockMode = GameMode.CLASSIC),
        ThemeData(LightBrownColorScheme, unlockScore = 50, unlockMode = GameMode.LIGHTING_ROUND),
        ThemeData(LightBrownColorScheme, unlockScore = 50, unlockMode = GameMode.HARDCORE),
        ThemeData(LightBrownColorScheme, unlockScore = 100, unlockMode = GameMode.CLASSIC),
        ThemeData(LightBrownColorScheme, unlockScore = 100, unlockMode = GameMode.LIGHTING_ROUND),
        ThemeData(LightBrownColorScheme, unlockScore = 100, unlockMode = GameMode.HARDCORE),
    )
    val currentTheme = MutableStateFlow(themeDataList[0]) // TODO theme choice from room


    fun changeTheme(themeData: ThemeData) {
        if (!themeData.isLocked) {
            currentTheme.value.isActive = false
            themeData.isActive = true
            currentTheme.value = themeData
        }
    }
}