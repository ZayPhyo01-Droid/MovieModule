package com.ui.design

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimensions(
    val default: Dp = 0.dp,
    val spaceXXTiny: Dp = 2.dp,
    val spaceXTiny: Dp = 4.dp,
    val spaceExtraTiny: Dp = 6.dp,
    val spaceTiny: Dp = 8.dp,
    val spaceXSmall: Dp = 10.dp,
    val spaceSmall: Dp = 12.dp,
    val spaceXNormal: Dp = 14.dp,
    val spaceNormal: Dp = 16.dp,
    val spaceXMedium: Dp = 18.dp,
    val spaceMedium: Dp = 20.dp,
    val spaceMediumX: Dp = 22.dp,
    val spaceBig: Dp = 24.dp,
    val spaceXBig: Dp = 28.dp,
    val spaceXXBig: Dp = 30.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceLargeX: Dp = 40.dp,
    val spaceLargeXXX: Dp = 44.dp,
    val spaceExtraLarge: Dp = 48.dp,
    val spaceXLarge: Dp = 52.dp,
    val spaceXXLarge: Dp = 64.dp,
    val spaceXXXLarge: Dp = 80.dp,
    val spaceXXXXLarge: Dp = 100.dp,
    val genericHorizontalMargin: Dp = spaceMedium,
    val genericBottomMargin: Dp = spaceLargeX,
    // Generic dimension use in all home screen toolbar height
    val originToolbarHeight: Dp = 56.dp,
    val genericToolbarHeight: Dp = 80.dp,
    val toolbarSpacingX: Dp = 70.dp,
    val topPaddingBottomSheet: Dp = 42.dp,
    val bottomPaddingBottomSheet: Dp = 40.dp,
    val bottomSheetGenericContentHeightFraction: Float = 0.93f,
    val smallDividerThick: Dp = 1.dp,
    val largeDividerThick: Dp = 6.dp,
    val borderStrokeWidth: Dp = 1.dp,
    val textFieldMiniHeight: Dp = 48.dp
)

private val LocalSpacing = compositionLocalOf { Dimensions() }

val spacing: Dimensions
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current