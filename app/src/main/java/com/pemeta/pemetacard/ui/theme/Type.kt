package com.pemeta.pemetacard.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pemeta.pemetacard.R

// Custom Font
val EncodeSans = FontFamily(
    Font(R.font.encode_sans_condensed_black, FontWeight.Black),
    Font(R.font.encode_sans_condensed_bold, FontWeight.Bold),
    Font(R.font.encode_sans_condensed_light, FontWeight.Light),
    Font(R.font.encode_sans_condensed_medium, FontWeight.Medium),
    Font(R.font.encode_sans_condensed_extra_bold, FontWeight.ExtraBold),
    Font(R.font.encode_sans_condensed_extra_light, FontWeight.ExtraLight),
    Font(R.font.encode_sans_condensed_regular, FontWeight.Normal),
    Font(R.font.encode_sans_condensed_semi_bold, FontWeight.SemiBold),
    Font(R.font.encode_sans_condensed_thin, FontWeight.Thin),
)

// Custom Typography
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = EncodeSans,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = EncodeSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = EncodeSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = EncodeSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = EncodeSans,
        fontWeight = FontWeight.Black,
        fontSize = 18.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = EncodeSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = EncodeSans,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = EncodeSans,
        fontWeight = FontWeight.Black,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = EncodeSans,
        fontWeight = FontWeight.Black,
        fontSize = 12.sp
    ),
    titleSmall = TextStyle(
        fontFamily = EncodeSans,
        fontWeight = FontWeight.Thin,
        fontSize = 12.sp
    )
)

// Original Typography

// Set of Material typography styles to start with
/* val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
) */