package com.pemeta.pemetacard.liquidglass.utils

import androidx.compose.runtime.Composable

@Composable
fun Block(content: @Composable () -> Unit) {
    content()
}