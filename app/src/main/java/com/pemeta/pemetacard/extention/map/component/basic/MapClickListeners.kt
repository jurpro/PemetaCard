package com.pemeta.pemetacard.extention.map.component.basic

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.android.gms.maps.model.IndoorBuilding
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PointOfInterest

/**
 * Default implementation of [IndoorStateChangeListener] with no-op
 * implementations.
 */
public object DefaultIndoorStateChangeListener : IndoorStateChangeListener

/**
 * Interface definition for building indoor level state changes.
 */
public interface IndoorStateChangeListener {
    /**
     * Callback invoked when an indoor building comes to focus.
     */
    public fun onIndoorBuildingFocused() {}

    /**
     * Callback invoked when a level for a building is activated.
     * @param building the activated building
     */
    public fun onIndoorLevelActivated(building: IndoorBuilding) {}
}

/**
 * Holder class for top-level click listeners.
 */
internal class MapClickListeners {
    var indoorStateChangeListener: IndoorStateChangeListener by mutableStateOf(
        DefaultIndoorStateChangeListener
    )
    var onMapClick: (LatLng) -> Unit by mutableStateOf({})
    var onMapLongClick: (LatLng) -> Unit by mutableStateOf({})
    var onMapLoaded: () -> Unit by mutableStateOf({})
    var onMyLocationButtonClick: () -> Boolean by mutableStateOf({ false })
    var onMyLocationClick: (Location) -> Unit by mutableStateOf({})
    var onPOIClick: (PointOfInterest) -> Unit by mutableStateOf({})
}
