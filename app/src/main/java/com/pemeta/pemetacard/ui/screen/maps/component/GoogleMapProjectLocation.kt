package com.pemeta.pemetacard.ui.screen.maps.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.pemeta.pemetacard.extention.map.component.basic.CameraPositionState
import com.pemeta.pemetacard.extention.map.component.basic.GoogleMap
import com.pemeta.pemetacard.extention.map.component.basic.MapProperties
import com.pemeta.pemetacard.extention.map.component.basic.MapType
import com.pemeta.pemetacard.extention.map.component.basic.MapUiSettings
import com.pemeta.pemetacard.extention.map.component.basic.Marker
import com.pemeta.pemetacard.extention.map.component.basic.MarkerState
import com.pemeta.pemetacard.extention.map.component.basic.rememberCameraPositionState
import com.pemeta.pemetacard.model.pengalaman.model.CompanyExperience
import kotlinx.coroutines.flow.first

@Composable
fun GoogleMapProjectLocation(
    project: CompanyExperience,
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState = rememberCameraPositionState(),
    onMapLoaded: () -> Unit = {},
){
    val listMarkerProject = project.coordinate

    val mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.HYBRID))
    }
    val uiSettings by remember { mutableStateOf(MapUiSettings(compassEnabled = true)) }

    LaunchedEffect(listMarkerProject) {
        if (listMarkerProject.isNotEmpty()) {
            val builder = LatLngBounds.Builder()

            listMarkerProject.forEach { marker ->
                builder.include(
                    LatLng(marker.latitude, marker.longitude)
                )
            }

            val bounds = builder.build()

            val padding = 100

            snapshotFlow { cameraPositionState.isMoving }
                .first()

            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngBounds(bounds, padding)
            )
        }
    }

    GoogleMap(
        modifier = modifier.fillMaxWidth(),
        properties = mapProperties,
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings,
        onMapLoaded = onMapLoaded
    ) {
        listMarkerProject.forEachIndexed { _, marker ->

            val locationProject = LatLng(marker.latitude, marker.longitude)

            Marker(
                state = MarkerState(position = locationProject),
                title = marker.title
            )
        }
    }
}