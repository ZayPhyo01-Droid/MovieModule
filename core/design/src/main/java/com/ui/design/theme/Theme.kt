package com.ui.design.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ui.design.theme.Typography

val LocalEntryPadding = compositionLocalOf {
    PaddingValues()
}

@Composable
fun MovieTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xffFF204E),
            secondary = Color(0xffFF9800),
            background = Color(0xff00224D),
            surface = Color(0xff00224D),
            onSurface = Color(0xffFFFFFF),
            onBackground = Color(0xffFFFFFF),
        ),
        typography = Typography,
        shapes = Shapes(
            medium = RoundedCornerShape(8.dp),
            small = RoundedCornerShape(4.dp),
            large = RoundedCornerShape(24.dp)
        ),
        content = content
    )
}