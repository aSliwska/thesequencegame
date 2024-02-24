package com.example.thesequencegame

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import androidx.core.view.WindowCompat
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.thesequencegame.ui.common_components.SequenceButton
import com.example.thesequencegame.ui.select_game_mode.SelectGameModeScreen
import com.example.thesequencegame.ui.theme.SequenceTypography
import com.example.thesequencegame.ui.theme.TheSequenceGameTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // make app fullscreen
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        setDecorFitsSystemWindows(window, false)

        setContent {
            TheSequenceGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                        TheSequenceGameApp()
                }
            }
        }
    }
}

@Composable
fun TheSequenceGameApp() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Column {
                    MiniSequenceGame()

                    Divider(
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(8.dp),
                    )

                    Surface(
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        Column {
                            DrawerMenuButton(
                                text = "New game",
                                icon = R.drawable.play_filled,
                                onClick = { /*TODO*/ }
                            )

                            DrawerMenuButton(
                                text = "High scores",
                                icon = R.drawable.leaderboard_filled,
                                onClick = { /*TODO*/ }
                            )

                            DrawerMenuButton(
                                text = "Themes",
                                icon = R.drawable.palette_filled,
                                onClick = { /*TODO*/ }
                            )

                            DrawerMenuButton(
                                text = "Settings",
                                icon = R.drawable.settings_filled,
                                onClick = { /*TODO*/ }
                            )
                        }
                    }


                }
            }
        },
    ) {
        SelectGameModeScreen(
            extendDrawer = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }
        )
    }
}

@Composable
private fun DrawerMenuButton(
    text: String,
    icon: Int,
    onClick: () -> Unit
) {
    SequenceButton(
        text = text,
        textColor = MaterialTheme.colorScheme.background,
        backgroundColor = Color.Transparent,
        icon = icon,
        contentDescription = text,
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        onClick = onClick
    )
}

@Composable
private fun MiniSequenceGame() {

}
