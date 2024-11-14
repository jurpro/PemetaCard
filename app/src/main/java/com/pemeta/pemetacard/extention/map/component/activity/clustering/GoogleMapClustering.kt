package com.pemeta.pemetacard.extention.map.component.activity.clustering

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.pemeta.pemetacard.extention.map.activity.MapClusteringActivity
import com.pemeta.pemetacard.extention.map.activity.singapore
import com.pemeta.pemetacard.extention.map.activity.singapore2
import com.pemeta.pemetacard.extention.map.component.basic.GoogleMap
import com.pemeta.pemetacard.extention.map.component.basic.MapsComposeExperimentalApi
import com.pemeta.pemetacard.extention.map.component.basic.MarkerInfoWindow
import com.pemeta.pemetacard.extention.map.component.basic.rememberCameraPositionState
import com.pemeta.pemetacard.extention.map.component.basic.rememberMarkerState
import com.pemeta.pemetacard.extention.map.component.utils.Clustering
import kotlin.random.Random

private val TAG = MapClusteringActivity::class.simpleName

@Composable
fun GoogleMapClustering() {
    val items = remember { mutableStateListOf<MyItem>() }
    LaunchedEffect(Unit) {
        for (i in 1..10) {
            val position = LatLng(
                singapore2.latitude + Random.nextFloat(),
                singapore2.longitude + Random.nextFloat(),
            )
            items.add(MyItem(position, "Marker", "Snippet"))
        }
    }
    GoogleMapClustering(items = items)
}

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun GoogleMapClustering(items: List<MyItem>) {
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }
    ) {
        Clustering(
            items = items,
            // Optional: Handle clicks on clusters, cluster items, and cluster item info windows
            onClusterClick = {
                Log.d(TAG, "Cluster clicked! $it")
                false
            },
            onClusterItemClick = {
                Log.d(TAG, "Cluster item clicked! $it")
                false
            },
            onClusterItemInfoWindowClick = {
                Log.d(TAG, "Cluster item info window clicked! $it")
            },
            // Optional: Custom rendering for clusters
            clusterContent = { cluster ->
                Surface(
                    Modifier.size(40.dp),
                    shape = CircleShape,
                    color = Color.Blue,
                    contentColor = Color.White,
                    border = BorderStroke(1.dp, Color.White)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            "%,d".format(cluster.size),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            },
            // Optional: Custom rendering for non-clustered items
            clusterItemContent = null
        )
        MarkerInfoWindow(
            state = rememberMarkerState(position = singapore),
            onClick = {
                Log.d(TAG, "Non-cluster marker clicked! $it")
                true
            }
        )
    }
}