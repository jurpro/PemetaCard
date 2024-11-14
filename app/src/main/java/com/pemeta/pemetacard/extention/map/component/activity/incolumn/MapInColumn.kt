package com.pemeta.pemetacard.extention.map.component.activity.incolumn

import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.pemeta.pemetacard.extention.map.component.basic.CameraPositionState

private const val TAG = "ScrollingMapActivity"

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MapInColumn(
    modifier: Modifier = Modifier,
    cameraPositionState: CameraPositionState,
    columnScrollingEnabled: Boolean,
    onMapTouched: () -> Unit,
    onMapLoaded: () -> Unit,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        var isMapLoaded by remember { mutableStateOf(false) }

        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState(),
                    columnScrollingEnabled
                ),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.padding(10.dp))
            for (i in 1..20) {
                Text(
                    text = "Item $i",
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .testTag("Item $i")
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                GoogleMapViewInColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("Map")
                        .pointerInteropFilter(
                            onTouchEvent = {
                                when (it.action) {
                                    MotionEvent.ACTION_DOWN -> {
                                        onMapTouched()
                                        false
                                    }

                                    else -> {
                                        Log.d(
                                            TAG,
                                            "MotionEvent ${it.action} - this never triggers."
                                        )
                                        true
                                    }
                                }
                            }
                        ),
                    cameraPositionState = cameraPositionState,
                    onMapLoaded = {
                        isMapLoaded = true
                        onMapLoaded()
                    },
                )
                if (!isMapLoaded) {
                    androidx.compose.animation.AnimatedVisibility(
                        modifier = Modifier
                            .fillMaxSize(),
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
            Spacer(modifier = Modifier.padding(10.dp))
            for (i in 21..40) {
                Text(
                    text = "Item $i",
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .testTag("Item $i")
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}