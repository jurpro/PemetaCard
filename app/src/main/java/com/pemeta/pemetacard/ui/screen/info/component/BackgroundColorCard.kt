package com.pemeta.pemetacard.ui.screen.info.component

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

class BackgroundColorCard {
    val glassBrush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.25f),
            Color.White.copy(alpha = 0.1f)
        )
    )
}