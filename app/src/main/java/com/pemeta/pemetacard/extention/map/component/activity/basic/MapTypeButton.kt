package com.pemeta.pemetacard.extention.map.component.activity.basic

import androidx.compose.runtime.Composable
import com.pemeta.pemetacard.extention.map.component.basic.MapType

@Composable
fun MapTypeButton(
    type: MapType,
    onClick: () -> Unit
) = MapButton(
    text = type.toString(),
    onClick = onClick
)
