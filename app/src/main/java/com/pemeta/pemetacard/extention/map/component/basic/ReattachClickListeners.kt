package com.pemeta.pemetacard.extention.map.component.basic

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.remember
import com.google.android.gms.maps.GoogleMap
import com.pemeta.pemetacard.extention.map.component.basic.MapApplier

/**
 * Returns a lambda that, when invoked, will reattach click listeners set by the [MapApplier] on
 * the [GoogleMap].
 * Used for working around other functionality that modifies those click listeners, such as
 * clustering.
 */
@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
@Composable
public fun rememberReattachClickListenersHandle(): () -> Unit {
    val map = currentComposer.applier as MapApplier
    return remember(map) {
        { map.attachClickListeners() }
    }
}
