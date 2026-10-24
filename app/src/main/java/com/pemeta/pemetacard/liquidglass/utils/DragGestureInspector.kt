package com.pemeta.pemetacard.liquidglass.utils

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.AwaitPointerEventScope
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.util.fastFirstOrNull

suspend fun PointerInputScope.inspectDragGestures(
    onDragStart: (down: PointerInputChange) -> Unit = {},
    onDragEnd: (change: PointerInputChange) -> Unit = {},
    onDragCancel: () -> Unit = {},
    onDrag: (change: PointerInputChange, dragAmount: Offset) -> Unit
) {
    awaitEachGesture {
     //   val initialDown = awaitFirstDown(false, PointerEventPass.Initial)

       // val down = awaitFirstDown(false)
       // val drag = initialDown

        // PERBAIKAN 1: Gunakan satu kali awaitFirstDown pada pass Main (default)
        // agar selaras dengan deteksi klik standar Android.
        val down = awaitFirstDown(requireUnconsumed = false)

        onDragStart(down)
        // Kirim event awal dengan nilai offset nol agar animasi tahu posisi awal jari.
        onDrag(down, Offset.Zero)

     //   onDragStart(down)
      //  onDrag(drag, Offset.Zero)
        val upEvent =
            drag(
              //  pointerId = drag.id,
                pointerId = down.id,
               // onDrag = { onDrag(it, it.positionChange()) }
                onDrag = { change ->
                    // PERBAIKAN 2: Teruskan posisi perubahan drag asli ke fungsi callback
                    onDrag(change, change.positionChange())
                    // Konsumsi event agar tidak diambil alih oleh container scroll luar
                    change.consume()
                }
            )
        if (upEvent == null) {
            onDragCancel()
        } else {
            onDragEnd(upEvent)
        }
    }
}

private suspend inline fun AwaitPointerEventScope.drag(
    pointerId: PointerId,
    onDrag: (PointerInputChange) -> Unit
): PointerInputChange? {
    val isPointerUp = currentEvent.changes.fastFirstOrNull { it.id == pointerId }?.pressed != true
    if (isPointerUp) {
        return null
    }
    var pointer = pointerId
    while (true) {
        val change = awaitDragOrUp(pointer) ?: return null
     /*   if (change.isConsumed) {
            return null
        }
        if (change.changedToUpIgnoreConsumed()) {
            return change
        } */

        // PERBAIKAN 3: Jangan batalkan drag jika dikonsumsi komponen internal kita sendiri,
        // melainkan cek apakah interaksinya benar-benar sudah dilepas oleh user.
        if (change.changedToUpIgnoreConsumed()) {
            return change
        }

        onDrag(change)
        pointer = change.id
    }
}

private suspend inline fun AwaitPointerEventScope.awaitDragOrUp(
    pointerId: PointerId
): PointerInputChange? {
    var pointer = pointerId
    while (true) {
        val event = awaitPointerEvent()
        val dragEvent = event.changes.fastFirstOrNull { it.id == pointer } ?: return null
        if (dragEvent.changedToUpIgnoreConsumed()) {
           /* val otherDown = event.changes.fastFirstOrNull { it.pressed }
            if (otherDown == null) {
                return dragEvent
            } else {
                pointer = otherDown.id
            } */
            // Jika jari utama diangkat tapi masih ada jari lain (multi-touch), alihkan pointer id-nya
            val otherDown = event.changes.fastFirstOrNull { it.pressed }
            if (otherDown == null) {
                return dragEvent
            } else {
                pointer = otherDown.id
            }
        } else {
            val hasDragged = dragEvent.previousPosition != dragEvent.position
            if (hasDragged) {
                return dragEvent
            }
        }
    }
}