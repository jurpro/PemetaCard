package com.pemeta.pemetacard.liquidglass.utils

import androidx.compose.runtime.withFrameMillis
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

/* suspend fun awaitFrame() {
    delay(1000L / 60L)
} */

// Ganti fungsi delay manual Anda dengan ini:
suspend fun awaitFrame() {
    withFrameMillis {
        // Mengunci eksekusi tepat pada frame berikutnya yang dirender oleh sistem OS
    }
}