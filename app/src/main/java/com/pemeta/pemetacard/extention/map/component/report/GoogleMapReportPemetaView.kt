package com.pemeta.pemetacard.extention.map.component.report

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.pemeta.pemetacard.extention.map.component.basic.CameraPositionState
import com.pemeta.pemetacard.extention.map.component.basic.DragState
import com.pemeta.pemetacard.extention.map.component.basic.GoogleMap
import com.pemeta.pemetacard.extention.map.component.basic.MapProperties
import com.pemeta.pemetacard.extention.map.component.basic.MapType
import com.pemeta.pemetacard.extention.map.component.basic.MapUiSettings
import com.pemeta.pemetacard.extention.map.component.basic.MarkerInfoWindowContent
import com.pemeta.pemetacard.extention.map.component.basic.rememberCameraPositionState
import com.pemeta.pemetacard.extention.map.component.basic.rememberMarkerState

private const val TAG = "ReportMapActivity"
val pemetaOffice = LatLng(-6.943474, 107.622712)
// val singapore2 = LatLng(1.40, 103.77)
// val singapore3 = LatLng(1.45, 103.77)
val defaultCameraPosition = CameraPosition.fromLatLngZoom(pemetaOffice, 16f)

@Composable
fun GoogleMapReportPemetaView(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val pemetaHeadOffice = rememberMarkerState(position = pemetaOffice)
    // val singapore2State = rememberMarkerState(position = singapore2)
    // val singapore3State = rememberMarkerState(position = singapore3)
    var circleCenter by remember { mutableStateOf(pemetaOffice) }

    if (pemetaHeadOffice.dragState == DragState.END) {
        circleCenter = pemetaHeadOffice.position
    }

    var uiSettings by remember { mutableStateOf(MapUiSettings(compassEnabled = false)) }
    var shouldAnimateZoom by remember { mutableStateOf(true) }
    var ticker by remember { mutableStateOf(0) }

    var mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    var mapVisible by remember { mutableStateOf(true) }

    if (mapVisible) {
        GoogleMap(
            modifier = modifier,
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = uiSettings,
            onMapLoaded = onMapLoaded,
            onPOIClick = {
                Log.d(TAG, "POI clicked: ${it.name}")
            }
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
                state = pemetaHeadOffice,
                title = "PT. Pemeta Engineering System",
                snippet = "Jl. Suryalaya Barat I No. 7 Bandung 40265 Jawa Barat",
                visible = true,
                // title = "Zoom in has been tapped $ticker times.",
                onClick = markerClick,
                draggable = true,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        it.title ?: "Title",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                    Text(
                        it.snippet ?: "Snippet",
                        textAlign = TextAlign.Center,
                    )
                }
            }
            /*
            MarkerOptions()
                .position(pemetaEngSys)
                .title(resources.getString(R.string.company_full_name))
                .snippet(resources.getString(R.string.company_address))
                .visible(true)

            MarkerInfoWindowContent(
                state = singapore2State,
                title = "Marker with custom info window.\nZoom in has been tapped $ticker times.",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                onClick = markerClick,
            ) {
                Text(it.title ?: "Title", color = Color.Blue)
            }
            Marker(
                state = singapore3State,
                title = "Marker in Singapore",
                onClick = markerClick
            )
            Circle(
                center = circleCenter,
                fillColor = MaterialTheme.colorScheme.secondary,
                strokeColor = MaterialTheme.colorScheme.secondaryContainer,
                radius = 1000.0,
            ) */
            content()
        }

    }
    Column {
        MapTypeReportPemetaControls(onMapTypeClick = {
            Log.d("GoogleMap", "Selected map type $it")
            mapProperties = mapProperties.copy(mapType = it)
        })
        Row {
            MapReportPemetaButton(
                text = "Reset Map",
                onClick = {
                    mapProperties = mapProperties.copy(mapType = MapType.NORMAL)
                    cameraPositionState.position = defaultCameraPosition
                    pemetaHeadOffice.position = pemetaOffice
                    pemetaHeadOffice.hideInfoWindow()
                }
            )
            MapReportPemetaButton(
                text = "Toggle Map",
                onClick = { mapVisible = !mapVisible },
                modifier = Modifier.testTag("toggleMapVisibility"),
            )
        }
        val coroutineScope = rememberCoroutineScope()
        /* ZoomReportControls(
            shouldAnimateZoom,
            uiSettings.zoomControlsEnabled,
            onZoomOut = {
                if (shouldAnimateZoom) {
                    coroutineScope.launch {
                        cameraPositionState.animate(CameraUpdateFactory.zoomOut())
                    }
                } else {
                    cameraPositionState.move(CameraUpdateFactory.zoomOut())
                }
            },
            onZoomIn = {
                if (shouldAnimateZoom) {
                    coroutineScope.launch {
                        cameraPositionState.animate(CameraUpdateFactory.zoomIn())
                    }
                } else {
                    cameraPositionState.move(CameraUpdateFactory.zoomIn())
                }
                ticker++
            },
            onCameraAnimationCheckedChange = {
                shouldAnimateZoom = it
            },
            onZoomControlsCheckedChange = {
                uiSettings = uiSettings.copy(zoomControlsEnabled = it)
            }
        ) */
        // DebugReportPemetaView(cameraPositionState, singaporeState)
    }
}