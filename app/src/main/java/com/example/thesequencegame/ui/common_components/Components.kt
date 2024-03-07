package com.example.thesequencegame.ui.common_components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import com.example.thesequencegame.R
import com.example.thesequencegame.ui.theme.SequenceTypography

@Composable
fun SequenceTitle(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    drawStyle: DrawStyle? = null,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = SequenceTypography.titleLarge.plus(
            TextStyle(drawStyle = drawStyle)
        ),
        color = color,
        fontWeight = fontWeight,
        textAlign = textAlign,
        modifier = modifier
    )
}

@Composable
fun SequenceTopBar(
    leftIcon: @Composable () -> Unit,
    rightIcon: @Composable () -> Unit = {},
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
    shape: Shape = MaterialTheme.shapes.large,
    textStyle: TextStyle = SequenceTypography.bodyMedium,
    arrangement: Arrangement.Horizontal = Arrangement.Center,
    contentDescription: String = "",
    textModifier: Modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 24.dp),
    rowModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val mutableInteractionSource = remember { MutableInteractionSource() }
    val isPressed = mutableInteractionSource.collectIsPressedAsState()
    val animatedScale = animateFloatAsState(if (isPressed.value) 0.9f else 1f, label = "animatedScale")
    val animatedAlpha = animateFloatAsState(if (isPressed.value) 0.5f else 1f, label = "animatedAlpha")

    Surface(
        color = backgroundColor,
        shape = shape,
        modifier = modifier
            .scale(animatedScale.value)
            .alpha(animatedAlpha.value)
            .clickable(
                enabled = enabled,
                interactionSource = mutableInteractionSource,
                indication = null
            ) {
                onClick()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = arrangement,
            modifier = rowModifier,
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(icon),
                    tint = textColor,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(48.dp)
                )
            }
            if (text != "") {
                Text(
                    text = text,
                    color = textColor,
                    style = textStyle,
                    modifier = textModifier,
                )
            }
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

@Composable
fun SequenceIconButtonWithLabel(
    icon: Int,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SequenceIconButton(
            icon = icon,
            contentDescription = label,
            onClick = onClick
        )

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun SequenceDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background,
            shape = MaterialTheme.shapes.extraLarge,
            modifier = modifier,
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SequenceSlider(
    value: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit,
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        colors = SliderDefaults.colors(
            activeTrackColor = MaterialTheme.colorScheme.surface,
            activeTickColor = MaterialTheme.colorScheme.surface,
            inactiveTrackColor = MaterialTheme.colorScheme.primary,
            inactiveTickColor = MaterialTheme.colorScheme.primary,
        ),
        valueRange = valueRange,
        steps = valueRange.endInclusive.toInt() - valueRange.start.toInt() + 1,
        thumb =  { sliderPositions ->
            Surface(
                shape = MaterialTheme.shapes.extraSmall,
                color = if (valueRange.start == value) MaterialTheme.colorScheme.tertiary
                    else MaterialTheme.colorScheme.surface,
                modifier = Modifier.size(20.dp)
            ) {
                if (sliderPositions.activeRange.start == value) {
                    Icon(
                        painter = painterResource(id = R.drawable.cross),
                        tint = MaterialTheme.colorScheme.onTertiary,
                        contentDescription = "zero",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        modifier = modifier,
    )
}
@Composable
fun SequenceDropDownMenu(
    chosenOption: Int,
    options: Map<Int, String>,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        // not expanded content
        SequenceDropDownMenuHeader(
            expanded = expanded,
            text = options[chosenOption]!!, // TODO !! null cast :/
            onClick = { expanded = true },
        )

        if (expanded) {
            Popup(
                onDismissRequest = { expanded = false },
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.inverseSurface,
                    shape = MaterialTheme.shapes.large,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        SequenceDropDownMenuHeader(
                            expanded = expanded,
                            text = options[chosenOption]!!, // TODO !! null cast :/
                            onClick = { expanded = false },
                        )

                        for (option in options) {
                            if (option.value != options[chosenOption]) {
                                Row(
                                    horizontalArrangement = Arrangement.Start,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(top = 12.dp, bottom = 12.dp, end = 8.dp, start = 16.dp)
                                        .clickable {
                                            expanded = false
                                            /*TODO*/
                                        }
                                ) {
                                    Text(
                                        text = option.value,
                                        style = MaterialTheme.typography.bodyMedium,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SequenceDropDownMenuHeader(
    expanded: Boolean,
    text: String,
    onClick: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        shape = MaterialTheme.shapes.large,
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, end = 8.dp, start = 16.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium
            )

            Icon(
                painter = painterResource(
                    id = if (expanded) R.drawable.arrow_up
                    else R.drawable.arrow_down
                ),
                contentDescription = "drop down menu"
            )
        }
    }
}