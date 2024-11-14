package com.pemeta.pemetacard.extention.map.component.basic

import androidx.compose.runtime.Immutable

/**
 * Enumerates the different types of map tiles.
 */
@Immutable
public enum class MapType(public val value: Int) {
    NONE(com.google.android.gms.maps.GoogleMap.MAP_TYPE_NONE),
    NORMAL(com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL),
    SATELLITE(com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE),
    TERRAIN(com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN),
    HYBRID(com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID)
}
