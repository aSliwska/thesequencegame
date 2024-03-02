package com.example.thesequencegame.ui.themes

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.common_components.SequenceIconButton
import com.example.thesequencegame.ui.common_components.SequenceNavigationDrawer
import com.example.thesequencegame.ui.common_components.SequenceTitle
import com.example.thesequencegame.ui.common_components.SequenceTopBar
import com.example.thesequencegame.ui.game.GameMode
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ThemesScreen(
    navigator: DestinationsNavigator,
) {
    val viewModel = remember { ThemesViewModel }

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
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize()
            ) {
                SequenceTitle(text = "THEMES")

                Divider(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(top = 16.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 144.dp),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(viewModel.themeDataList) { themeData ->
                        ThemePreview(
                            themeData = themeData,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ThemePreview(
    themeData: ThemeData,
    viewModel: ThemesViewModel,
) {
    val mutableInteractionSource = remember { MutableInteractionSource() }
    val animatedBorder = animateDpAsState(if (themeData.isActive) 6.dp else 1.dp, label = "animatedBorder")

    Surface(
        color = themeData.colorScheme.background,
        modifier = Modifier
            .aspectRatio(1f)
            .border(
                BorderStroke(
                    animatedBorder.value,
                    if (themeData.isActive) themeData.colorScheme.secondary else themeData.colorScheme.inverseSurface
                )
            )
            .clickable(
                enabled = !themeData.isLocked,
                interactionSource = mutableInteractionSource,
                indication = null
            ) {
                viewModel.changeTheme(themeData)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            Surface(
                color = themeData.colorScheme.surface,
                shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 24.dp)
                ) {
                    IconSquare(
                        backgroundColor = themeData.colorScheme.inverseSurface,
                        iconColor = themeData.colorScheme.onSurface,
                        icon = R.drawable.heart,
                    )

                    IconSquare(
                        backgroundColor = themeData.colorScheme.primary,
                        iconColor = themeData.colorScheme.onPrimary,
                        icon = R.drawable.heart,
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                IconSquare(
                    backgroundColor = themeData.colorScheme.primary,
                )

                IconSquare(
                    backgroundColor = themeData.colorScheme.secondary,
                    iconColor = themeData.colorScheme.onSecondary,
                    icon = R.drawable.check,
                )

                IconSquare(
                    backgroundColor = themeData.colorScheme.tertiary,
                    iconColor = themeData.colorScheme.onTertiary,
                    icon = R.drawable.cross,
                )
            }
        }

        if (themeData.isLocked && themeData.unlockScore != null && themeData.unlockMode != null) {
            LockOverlay(
                unlockScore = themeData.unlockScore,
                unlockMode = themeData.unlockMode
            )
        }
    }
}

@Composable
private fun IconSquare(
    backgroundColor: Color,
    iconColor: Color? = null,
    icon: Int? = null,
) {
    Surface(
        color = backgroundColor,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth(0.65f)
            .aspectRatio(1f),
    ) {
        if (icon != null && iconColor != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    tint = iconColor,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(0.75f)
                )
            }
        }
    }
}

@Composable
private fun LockOverlay(
    unlockScore: Int,
    unlockMode: GameMode,
) {
    Surface(
        color = Color.Black.copy(alpha = 0.8f),
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 4.dp, horizontal = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.lock),
                tint = Color.White,
                contentDescription = "locked",
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .aspectRatio(1f)
            )

            Text(
                text = "Get score $unlockScore in ${unlockMode.titleName}",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall.plus(
                    TextStyle(
                        shadow = Shadow(color = Color.Black, blurRadius = 16f)
                    )
                ),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}