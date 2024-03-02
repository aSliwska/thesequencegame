package com.example.thesequencegame.ui.support_me

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.common_components.SequenceButton
import com.example.thesequencegame.ui.common_components.SequenceIconButton
import com.example.thesequencegame.ui.common_components.SequenceNavigationDrawer
import com.example.thesequencegame.ui.common_components.SequenceTitle
import com.example.thesequencegame.ui.common_components.SequenceTopBar
import com.example.thesequencegame.ui.theme.SequenceTypography
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SupportMeScreen(
    navigator: DestinationsNavigator
) {
    var showAdWatchedDialog by remember { mutableStateOf(false) }

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

                SequenceTitle(text = "SUPPORT ME", modifier = Modifier.weight(0.12f))

                ButtonCard(
                    icon = R.drawable.money,
                    name = "Donate",
                    description = "Buy me a coffee as a thanks for making the game :) " +
                            "You don't have to - but it is very much appreciated! <3",
                    modifier = Modifier.weight(0.17f)
                ) {
                    /*TODO*/
                }

                ButtonCard(
                    icon = R.drawable.watch_ad,
                    name = "Watch an ad",
                    description = "This app will never show any intrusive ads. You can, however, " +
                            "decide to watch an ad yourself to show support. Thank you kindly " +
                            "for considering this option ^^",
                    modifier = Modifier.weight(0.4f)
                ) {
                    /*TODO*/

                    showAdWatchedDialog = true
                }
            }
        }
    }

    if (showAdWatchedDialog) {
        AdWatchedDialog {
            showAdWatchedDialog = false
        }
    }
}

@Composable
private fun ButtonCard(
    icon: Int,
    name: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth(),
    ) {
        SequenceButton(
            icon = icon,
            text = name,
            rowModifier = Modifier.padding(start = 8.dp, end = 16.dp),
            textModifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 8.dp),
            onClick = onClick,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = description,
            style = SequenceTypography.labelSmall,
            textAlign = TextAlign.Justify,
        )
    }
}