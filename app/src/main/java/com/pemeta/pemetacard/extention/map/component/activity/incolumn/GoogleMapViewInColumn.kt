package com.pemeta.pemetacard.extention.map.component.activity.incolumn

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.Marker
import com.pemeta.pemetacard.extention.map.activity.singapore
import com.pemeta.pemetacard.extention.map.component.basic.CameraPositionState
import com.pemeta.pemetacard.extention.map.component.basic.GoogleMap
import com.pemeta.pemetacard.extention.map.component.basic.MapProperties
import com.pemeta.pemetacard.extention.map.component.basic.MapType
import com.pemeta.pemetacard.extention.map.component.basic.MapUiSettings
import com.pemeta.pemetacard.extention.map.component.basic.MarkerInfoWindowContent
import com.pemeta.pemetacard.extention.map.component.basic.rememberMarkerState

private const val TAG = "ScrollingMapActivity"

@Composable
fun GoogleMapViewInColumn(
    modifier: Modifier,
    cameraPositionState: CameraPositionState,
    onMapLoaded: () -> Unit,
) {
    val singaporeState = rememberMarkerState(position = singapore)

    var uiSettings by remember { mutableStateOf(MapUiSettings(compassEnabled = false)) }
    var mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = uiSettings,
        onMapLoaded = onMapLoaded
    ) {
        // Drawing on the map is accomplished with a child-based API
        val markerClick: (Marker) -> Boolean = {
            Log.d(TAG, "${it.title} was clicked")
            cameraPositionState.projection?.let { projection ->
                Log.d(TAG, "The current projection is: $projection")
            }
            false
        }
        MarkerInfoWindowContent(
            state = singaporeState,
            title = "Singapore",
            onClick = markerClick,
            draggable = true,
        ) {
            Text(it.title ?: "Title", color = Color.Red)
        }
    }
}