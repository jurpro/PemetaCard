package com.pemeta.pemetacard.extention.map.component.basic

import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import java.util.Objects

internal val DefaultMapProperties = MapProperties()

/**
 * Data class for properties that can be modified on the map.
 *
 * Note: This is intentionally a class and not a data class for binary
 * compatibility on future changes.
 * See: https://jakewharton.com/public-api-challenges-in-kotlin/
 */
public class MapProperties(
    public val isBuildingEnabled: Boolean = false,
    public val isIndoorEnabled: Boolean = false,
    public val isMyLocationEnabled: Boolean = false,
    public val isTrafficEnabled: Boolean = false,
    public val latLngBoundsForCameraTarget: LatLngBounds? = null,
    public val mapStyleOptions: MapStyleOptions? = null,
    public val mapType: MapType = MapType.NORMAL,
    public val maxZoomPreference: Float = 21.0f,
    public val minZoomPreference: Float = 3.0f,
) {
    override fun toString(): String = "MapProperties(" +
        "isBuildingEnabled=$isBuildingEnabled, isIndoorEnabled=$isIndoorEnabled, " +
        "isMyLocationEnabled=$isMyLocationEnabled, isTrafficEnabled=$isTrafficEnabled, " +
        "latLngBoundsForCameraTarget=$latLngBoundsForCameraTarget, mapStyleOptions=$mapStyleOptions, " +
        "mapType=$mapType, maxZoomPreference=$maxZoomPreference, " +
        "minZoomPreference=$minZoomPreference)"

    override fun equals(other: Any?): Boolean = other is MapProperties &&
        isBuildingEnabled == other.isBuildingEnabled &&
        isIndoorEnabled == other.isIndoorEnabled &&
        isMyLocationEnabled == other.isMyLocationEnabled &&
        isTrafficEnabled == other.isTrafficEnabled &&
        latLngBoundsForCameraTarget == other.latLngBoundsForCameraTarget &&
        mapStyleOptions == other.mapStyleOptions &&
        mapType == other.mapType &&
        maxZoomPreference == other.maxZoomPreference &&
        minZoomPreference == other.minZoomPreference

    override fun hashCode(): Int = Objects.hash(
        isBuildingEnabled,
        isIndoorEnabled,
        isMyLocationEnabled,
        isTrafficEnabled,
        latLngBoundsForCameraTarget,
        mapStyleOptions,
        mapType,
        maxZoomPreference,
        minZoomPreference
    )

    public fun copy(
        isBuildingEnabled: Boolean = this.isBuildingEnabled,
        isIndoorEnabled: Boolean = this.isIndoorEnabled,
        isMyLocationEnabled: Boolean = this.isMyLocationEnabled,
        isTrafficEnabled: Boolean = this.isTrafficEnabled,
        latLngBoundsForCameraTarget: LatLngBounds? = this.latLngBoundsForCameraTarget,
        mapStyleOptions: MapStyleOptions? = this.mapStyleOptions,
        mapType: MapType = this.mapType,
        maxZoomPreference: Float = this.maxZoomPreference,
        minZoomPreference: Float = this.minZoomPreference,
    ): MapProperties = MapProperties(
        isBuildingEnabled = isBuildingEnabled,
        isIndoorEnabled = isIndoorEnabled,
        isMyLocationEnabled = isMyLocationEnabled,
        isTrafficEnabled = isTrafficEnabled,
        latLngBoundsForCameraTarget = latLngBoundsForCameraTarget,
        mapStyleOptions = mapStyleOptions,
        mapType = mapType,
        maxZoomPreference = maxZoomPreference,
        minZoomPreference = minZoomPreference,
    )
}

