package com.example.thesequencegame.ui.common_components

import android.graphics.drawable.Icon
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.thesequencegame.ui.theme.SequenceTypography


@Composable
fun SequenceTitle(text: String) {
    Text(
        text = text,
        style = SequenceTypography.titleLarge
    )
}

@Composable
fun SequenceTopBar(
    leftIcon: @Composable () -> Unit,
    rightIcon: @Composable () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 32.dp,
                start = 16.dp,
                end = 16.dp,
            )
    ) {
        leftIcon()
        rightIcon()
    }
}

@Composable
fun SequenceButton(
    text: String = "",
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    icon: Int? = null,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val mutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed = mutableInteractionSource.collectIsPressedAsState()
    val animatedScale = animateFloatAsState(if (isPressed.value) 0.95f else 1f, label = "animatedScale")
    val animatedAlpha = animateFloatAsState(if (isPressed.value) 0.8f else 1f, label = "animatedAlpha")

    Surface(
        color = backgroundColor,
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .scale(animatedScale.value)
            .alpha(animatedAlpha.value)
            .clickable(interactionSource = mutableInteractionSource, indication = null) {
                onClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(icon),
                    tint = textColor,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(48.dp)
                )
            }
            Text(
                text = text,
                color = textColor,
                style = SequenceTypography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 24.dp)
            )
        }
    }
}

@Composable
fun SequenceIconButton(
    icon: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    Surface(
        color = Color.Transparent,
        onClick = onClick,
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier.size(56.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = contentDescription,
            modifier = Modifier.padding(4.dp)
        )
    }
}