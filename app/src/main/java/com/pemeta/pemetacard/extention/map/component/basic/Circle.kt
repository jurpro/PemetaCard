package com.pemeta.pemetacard.extention.map.component.basic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PatternItem
import com.google.maps.android.ktx.addCircle

internal class CircleNode(
    val circle: Circle,
    var onCircleClick: (Circle) -> Unit
) : MapNode {
    override fun onRemoved() {
        circle.remove()
    }
}

/**
 * A composable for a circle on the map.
 *
 * @param center the [LatLng] to use for the center of this circle
 * @param clickable boolean indicating if the circle is clickable or not
 * @param fillColor the fill color of the circle
 * @param radius the radius of the circle in meters.
 * @param strokeColor the stroke color of the circle
 * @param strokePattern a sequence of [PatternItem] to be repeated along the circle's outline (null
 * represents a solid line)
 * @param tag optional tag to be associated with the circle
 * @param strokeWidth the width of the circle's outline in screen pixels
 * @param visible the visibility of the circle
 * @param zIndex the z-index of the circle
 * @param onClick a lambda invoked when the circle is clicked
 */
@Composable
@GoogleMapComposable
public fun Circle(
    center: LatLng,
    clickable: Boolean = false,
    fillColor: Color = Color.Transparent,
    radius: Double = 0.0,
    strokeColor: Color = Color.Black,
    strokePattern: List<PatternItem>? = null,
    strokeWidth: Float = 10f,
    tag: Any? = null,
    visible: Boolean = true,
    zIndex: Float = 0f,
    onClick: (Circle) -> Unit = {},
) {
    val mapApplier = currentComposer.applier as? MapApplier
    ComposeNode<CircleNode, MapApplier>(
        factory = {
            val circle = mapApplier?.map?.addCircle {
                center(center)
                clickable(clickable)
                fillColor(fillColor.toArgb())
                radius(radius)
                strokeColor(strokeColor.toArgb())
                strokePattern(strokePattern)
                strokeWidth(strokeWidth)
                visible(visible)
                zIndex(zIndex)
            } ?: error("Error adding circle")
            circle.tag = tag
            CircleNode(circle, onClick)
        },
        update = {
            update(onClick) { this.onCircleClick = it }

            set(center) { this.circle.center = it }
            set(clickable) { this.circle.isClickable = it }
            set(fillColor) { this.circle.fillColor = it.toArgb() }
            set(radius) { this.circle.radius = it }
            set(strokeColor) { this.circle.strokeColor = it.toArgb() }
            set(strokePattern) { this.circle.strokePattern = it }
            set(strokeWidth) { this.circle.strokeWidth = it }
            set(tag) { this.circle.tag = it }
            set(visible) { this.circle.isVisible = it }
            set(zIndex) { this.circle.zIndex = it }
        }
    )
}