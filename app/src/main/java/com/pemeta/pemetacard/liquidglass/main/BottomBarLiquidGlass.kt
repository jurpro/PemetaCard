package com.pemeta.pemetacard.liquidglass.main

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kyant.backdrop.Backdrop
import com.kyant.backdrop.backdrops.rememberLayerBackdrop
import com.pemeta.pemetacard.liquidglass.component.BottomBarItems
import com.pemeta.pemetacard.liquidglass.component.LiquidBottomTab
import com.pemeta.pemetacard.liquidglass.component.LiquidBottomTabs
import com.pemeta.pemetacard.liquidglass.content.FlightIcon
import com.pemeta.pemetacard.liquidglass.utils.Block
import com.pemeta.pemetacard.navigation.ScreenMenu
import kotlinx.coroutines.launch

@Composable
fun BottomBarLiquidGlass(
    backdrop: Backdrop,
    homeListState: LazyListState,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val coroutineScope = rememberCoroutineScope()

    val isLightTheme = !isSystemInDarkTheme()
    val contentColor = if (isLightTheme) Color.Black else Color.White

    val airplaneModeIcon = rememberVectorPainter(FlightIcon)
    val iconColorFilter = ColorFilter.tint(contentColor)

    val tabItems = BottomBarItems.getItems()
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .padding(top = 8.dp, bottom = 16.dp)
    ){
        Block {
            LiquidBottomTabs(
                selectedTabIndex = { selectedTabIndex },
              //  onTabSelected = { selectedTabIndex = it },
                onTabSelected = { index ->
                    val item = tabItems[index]

                    val isSelected = currentRoute == item.screen.route

                    if (isSelected && item.screen.route == ScreenMenu.Info.route) {
                        coroutineScope.launch {
                            homeListState.animateScrollToItem(0)
                        }
                    } else if (!isSelected) {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }

                  /*  if (currentRoute != item.screen.route) {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    } */
                },
                backdrop = backdrop,
                tabsCount = tabItems.size,
                modifier = Modifier.padding(horizontal = 16f.dp)
            ) {
                repeat(tabItems.size) { index ->
                    val item = tabItems[index]

                    LiquidBottomTab(
                        onClick = { selectedTabIndex = index }
                    ) {
                        val tabItemIcon = rememberVectorPainter(item.icon)
                        Box(
                            Modifier
                                .size(28f.dp)
                                .paint(
                                    painter = tabItemIcon,
                                    colorFilter = iconColorFilter
                                )
                        )
                        BasicText(
                            text = item.title,
                            style = TextStyle(contentColor, 11f.sp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarLiquidGlassPreview(){
    val backdrop = rememberLayerBackdrop()

    BottomBarLiquidGlass(
        backdrop = backdrop,
        navController = rememberNavController(),
        homeListState = rememberLazyListState()
    )
}