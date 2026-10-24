package com.pemeta.pemetacard.ui.screen.maps.component

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.pemeta.pemetacard.extention.map.component.basic.rememberCameraPositionState
import com.pemeta.pemetacard.model.pengalaman.coredata.DataPemetaExperience

@Composable
fun MapsProjectPemeta(
    id: Int,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val experienceData = DataPemetaExperience.CompanyExperienceList[id-1]

    Column (
        modifier
            .height(250.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(color = MaterialTheme.colorScheme.surface),
    ) {

        var isMapLoaded by remember { mutableStateOf(false) }
        // Observing and controlling the camera's state can be done with a CameraPositionState
        val projectLocationCoordinates = LatLng(experienceData.latitude, experienceData.longitude)
        val cameraPositionProject = CameraPosition.fromLatLngZoom(projectLocationCoordinates, 10f)

        val cameraPositionState = rememberCameraPositionState {
            position = cameraPositionProject
        }

        Box(
            modifier.fillMaxSize()
        ) {
            GoogleMapProjectLocation(
                project = experienceData,
                modifier = modifier
                    .matchParentSize(),
                // .padding(bottom = 100.dp),
                cameraPositionState = cameraPositionState,
                onMapLoaded = {
                    isMapLoaded = true
                },
            )
            if (!isMapLoaded) {
                androidx.compose.animation.AnimatedVisibility(
                    modifier = modifier
                        .matchParentSize(),
                    visible = !isMapLoaded,
                    enter = EnterTransition.None,
                    exit = fadeOut()
                ) {
                    CircularProgressIndicator(
                        modifier
                            .background(MaterialTheme.colorScheme.background)
                            .wrapContentSize()
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = PIXEL_9_PRO)
fun MapsProjectPemetaPreview(){
    val experienceData = DataPemetaExperience.CompanyExperienceList[2]
    MapsProjectPemeta(
        id = experienceData.id
    )
}