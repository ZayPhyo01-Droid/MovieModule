package com.ui.design.effect

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.pulsate(
    onClick: () -> Unit
) = composed {
    val buttonState = remember { MutableInteractionSource() }
    val scale = animateFloatAsState(if (buttonState.collectIsPressedAsState().value) 0.92f else 1f)

    this
        .graphicsLayer {
            scaleX = scale.value
            scaleY = scale.value
        }
        .clickable(
            interactionSource = buttonState,
            indication = null,
            onClick = onClick
        )
}