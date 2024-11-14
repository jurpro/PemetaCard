package com.pemeta.pemetacard.extention.map.component.report

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.pemeta.pemetacard.R
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

val pemetaOfficeCenter = LatLng(-6.943474, 107.622712)
val defaultCameraPositionBandung = CameraPosition.fromLatLngZoom(pemetaOfficeCenter, 16f)

@Composable
fun GoogleMapAboutPemetaView(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () -> Unit = {},
    content: @Composable () -> Unit = {}
){

    val pemetaOfficeBandung = rememberMarkerState(position = pemetaOfficeCenter)

    var circleCenter by remember { mutableStateOf(pemetaOfficeCenter) }

    if (pemetaOfficeBandung.dragState == DragState.END) {
        circleCenter = pemetaOfficeBandung.position
    }

    val uiSettings by remember { mutableStateOf(MapUiSettings(compassEnabled = false)) }
    var shouldAnimateZoom by remember { mutableStateOf(true) }
    var ticker by remember { mutableStateOf(0) }

    val mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }
    val mapVisible by remember { mutableStateOf(true) }

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
                state = pemetaOfficeBandung,
                title = stringResource(id = R.string.company_full_name),
                snippet = stringResource(id = R.string.company_address),
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

            content()
        }
    }
}