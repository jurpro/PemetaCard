package com.pemeta.pemetacard.ui.screen.maps

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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pemeta.pemetacard.extention.map.component.basic.rememberCameraPositionState
import com.pemeta.pemetacard.extention.map.component.report.GoogleMapAboutPemetaView
import com.pemeta.pemetacard.extention.map.component.report.defaultCameraPositionBandung
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun MapsOfficePemeta(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    Column (
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(color = MaterialTheme.colorScheme.surface),
    ) {

        var isMapLoaded by remember { mutableStateOf(false) }
        // Observing and controlling the camera's state can be done with a CameraPositionState
        val cameraPositionState = rememberCameraPositionState {
            position = defaultCameraPositionBandung
        }

        Box(Modifier.fillMaxSize()) {
            GoogleMapAboutPemetaView(
                modifier = Modifier
                    .matchParentSize(),
                // .padding(bottom = 100.dp),
                cameraPositionState = cameraPositionState,
                onMapLoaded = {
                    isMapLoaded = true
                },
            )
            if (!isMapLoaded) {
                androidx.compose.animation.AnimatedVisibility(
                    modifier = Modifier
                        .matchParentSize(),
                    visible = !isMapLoaded,
                    enter = EnterTransition.None,
                    exit = fadeOut()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .wrapContentSize()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MapsOfficePemetaPreview(){
    PemetaCardTheme {
        MapsOfficePemeta()
    }
}