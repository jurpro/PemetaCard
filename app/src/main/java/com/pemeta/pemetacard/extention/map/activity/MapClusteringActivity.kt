package com.pemeta.pemetacard.extention.map.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.pemeta.pemetacard.extention.map.component.activity.clustering.GoogleMapClustering

class MapClusteringActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleMapClustering()
        }
    }
}

