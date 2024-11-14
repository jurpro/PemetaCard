package com.pemeta.pemetacard.extention.map.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.pemeta.pemetacard.extention.map.component.activity.incolumn.MapInColumn
import com.pemeta.pemetacard.extention.map.component.basic.rememberCameraPositionState

private const val TAG = "ScrollingMapActivity"

class MapInColumnActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Observing and controlling the camera's state can be done with a CameraPositionState
            val cameraPositionState = rememberCameraPositionState {
                position = defaultCameraPosition
            }
            var columnScrollingEnabled by remember { mutableStateOf(true) }

            // Use a LaunchedEffect keyed on the camera moving state to enable column scrolling when the camera stops moving
            LaunchedEffect(cameraPositionState.isMoving) {
                if (!cameraPositionState.isMoving) {
                    columnScrollingEnabled = true
                    Log.d(TAG, "Map camera stopped moving - Enabling column scrolling...")
                }
            }

            MapInColumn(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState,
                columnScrollingEnabled = columnScrollingEnabled,
                onMapTouched = {
                    columnScrollingEnabled = false
                    Log.d(
                        TAG,
                        "User touched map - Disabling column scrolling after user touched this Box..."
                    )
                },
                onMapLoaded = { }
            )
        }
    }
}
