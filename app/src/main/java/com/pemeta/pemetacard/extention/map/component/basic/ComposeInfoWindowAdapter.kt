package com.pemeta.pemetacard.extention.map.component.basic

import android.view.View
import androidx.compose.ui.platform.ComposeView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.Marker

/**
 * An InfoWindowAdapter that returns a [ComposeView] for drawing a marker's
 * info window.
 *
 * Note: As of version 18.0.2 of the Maps SDK, info windows are drawn by
 * creating a bitmap of the [View]s returned in the [GoogleMap.InfoWindowAdapter]
 * interface methods. The returned views are never attached to a window,
 * instead, they are drawn to a bitmap canvas. This breaks the assumption
 * [ComposeView] makes where it must eventually be attached to a window. As a
 * workaround, the contained window is temporarily attached to the MapView so
 * that the contents of the ComposeViews are rendered.
 *
 * Eventually when info windows are no longer implemented this way, this
 * implementation should be updated.
 */
internal class ComposeInfoWindowAdapter(
    private val mapView: MapView,
    private val markerNodeFinder: (Marker) -> MarkerNode?
) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker): View? {
        val markerNode = markerNodeFinder(marker) ?: return null
        val content  = markerNode.infoContent
        if (content == null) {
            return null
        }
        val view = ComposeView(mapView.context).apply {
            setContent { content(marker) }
        }
        mapView.renderComposeViewOnce(view, parentContext = markerNode.compositionContext)
        return view
    }

    override fun getInfoWindow(marker: Marker): View? {
        val markerNode = markerNodeFinder(marker) ?: return null
        val infoWindow  = markerNode.infoWindow
        if (infoWindow == null) {
            return null
        }
        val view = ComposeView(mapView.context).apply {
            setContent { infoWindow(marker) }
        }
        mapView.renderComposeViewOnce(view, parentContext = markerNode.compositionContext)
        return view
    }

}
