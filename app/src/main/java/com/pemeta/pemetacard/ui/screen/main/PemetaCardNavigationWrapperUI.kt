package com.pemeta.pemetacard.ui.screen.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults.containerColor
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.navigation.BottomBarItem
import com.pemeta.pemetacard.navigation.ScreenMenu
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme
import kotlinx.coroutines.launch

@Composable
fun PemetaCardNavigationWrapperUI(
    navController: NavHostController,
    homeListState: LazyListState,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
){
    NavigationBar(
        windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
       // val homeListState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        val items = listOf(
            BottomBarItem(
                title = stringResource(R.string.menu_info),
                icon = Icons.Default.Home,
                screen = ScreenMenu.Info
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_experience),
                icon = Icons.Default.Work,
                screen = ScreenMenu.Experience,
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_client),
                icon = Icons.Default.CheckCircle,
                screen = ScreenMenu.Client
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_team),
                icon = Icons.Default.People,
                screen = ScreenMenu.Team
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_legalitas),
                icon = Icons.Default.Menu,
                screen = ScreenMenu.LegalitasPemeta
            ),
        )

        items.forEach { item ->
            val isSelected = currentRoute == item.screen.route

            NavigationBarItem(
                selected = isSelected,
               // selected = currentRoute == item.screen.route,
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                onClick = {
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
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PemetaCardNavigationWrapperUIPreview(){
    PemetaCardTheme {
        PemetaCardNavigationWrapperUI(
            navController = rememberNavController(),
            homeListState = rememberLazyListState()
        )
    }
}