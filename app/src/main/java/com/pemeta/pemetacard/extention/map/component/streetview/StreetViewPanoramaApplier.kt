package com.pemeta.pemetacard.extention.map.component.streetview

import androidx.compose.runtime.AbstractApplier
import com.google.android.gms.maps.StreetViewPanorama
import com.pemeta.pemetacard.extention.map.component.basic.MapNode

private object StreetViewPanoramaNodeRoot : MapNode

internal class StreetViewPanoramaApplier(
    val streetViewPanorama: StreetViewPanorama
) : AbstractApplier<MapNode>(StreetViewPanoramaNodeRoot) {
    override fun onClear() { }

    override fun insertBottomUp(index: Int, instance: MapNode) {
        instance.onAttached()
    }

    override fun insertTopDown(index: Int, instance: MapNode) { }

    override fun move(from: Int, to: Int, count: Int) { }

    override fun remove(index: Int, count: Int) { }
}
