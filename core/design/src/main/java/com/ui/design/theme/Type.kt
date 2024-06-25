package com.ui.design.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ui.design.R

private val NantoSansFamily = FontFamily(
    Font(R.font.nanto_sans_regular),
    Font(R.font.nanto_sans_medium, FontWeight.Medium),
    Font(R.font.nanto_sans_bold, FontWeight.Bold)
)
private val MontserratFamily = FontFamily(
    Font(R.font.montserrat_regular),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)
private val ArichivoFamily = FontFamily(
    Font(R.font.arichivo_regular),
    Font(R.font.arichivo_medium, FontWeight.Medium),
    Font(R.font.arichivo_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        letterSpacing = 4.sp,
        fontFamily = ArichivoFamily,
        fontSize = 28.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = NantoSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = NantoSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.8.sp
    ),
    bodySmall = TextStyle(
        fontFamily = NantoSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.8.sp
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        letterSpacing = 4.sp,
        fontFamily = ArichivoFamily
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        letterSpacing = 4.sp,
        fontFamily = ArichivoFamily
    ),
    labelMedium = TextStyle(
        fontFamily = MontserratFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MontserratFamily,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.5.sp
    )

)