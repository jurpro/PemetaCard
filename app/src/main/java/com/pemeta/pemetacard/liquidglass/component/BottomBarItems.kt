package com.pemeta.pemetacard.liquidglass.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.liquidglass.content.FlightIcon
import com.pemeta.pemetacard.navigation.BottomBarItemLiquidGlass
import com.pemeta.pemetacard.navigation.ScreenMenu

object BottomBarItems {
    @Composable
    fun getItems(): List<BottomBarItemLiquidGlass>{
        return listOf(
            BottomBarItemLiquidGlass(
                title = stringResource(R.string.menu_info),
                icon = Icons.Default.Home,
               // icon = FlightIcon,
                screen = ScreenMenu.Info
            ),
            BottomBarItemLiquidGlass(
                title = stringResource(R.string.menu_experience),
                icon = Icons.Default.Work,
                screen = ScreenMenu.Experience,
            ),
            BottomBarItemLiquidGlass(
                title = stringResource(R.string.menu_client),
                icon = Icons.Default.CheckCircle,
                screen = ScreenMenu.Client
            ),
            BottomBarItemLiquidGlass(
                title = stringResource(R.string.menu_team),
                icon = Icons.Default.People,
                screen = ScreenMenu.Team
            ),
            BottomBarItemLiquidGlass(
                title = stringResource(R.string.menu_legalitas),
                icon = Icons.Default.Menu,
                screen = ScreenMenu.LegalitasPemeta
            ),
           /* BottomBarItemLiquidGlass(
                title = stringResource(R.string.menu_info),
                icon = FlightIcon,
                screen = ScreenMenu.Info
            ), */
        )
    }
}