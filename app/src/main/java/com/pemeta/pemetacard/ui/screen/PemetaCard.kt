package com.pemeta.pemetacard.ui.screen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults.containerColor
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.R
import com.pemeta.pemetacard.navigation.BottomBarItem
import com.pemeta.pemetacard.navigation.ScreenMenu
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pemeta.pemetacard.ui.screen.about.AboutScreen
import com.pemeta.pemetacard.ui.screen.client.detail.DetailClientScreen
import com.pemeta.pemetacard.ui.screen.experience.detail.DetailExperienceScreen
import com.pemeta.pemetacard.ui.screen.experience.list.ListExperienceScreen
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@Composable
fun PemetaCard(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != ScreenMenu.DetailExperience.route) {
                NavigationBar(navController)
            }
            if (currentRoute != ScreenMenu.DetailClient.route) {
                NavigationBar(navController)
            }
        },
        modifier = modifier
    ) { paddingValues ->

        NavHost(navController = navController, startDestination = ScreenMenu.Experience.route) {

           // composable(ScreenMenu.Home.route) { ListExperienceScreen(navController = navController, paddingValues = paddingValues) }

            composable(ScreenMenu.Experience.route) { ListExperienceScreen(navController = navController, paddingValues = paddingValues) }

           // composable(ScreenMenu.Chat.route) { CelebrityChatScreen( SampleDataChat.conversationSample ) }

           // composable(ScreenMenu.Favorite.route) { FavoriteScreen() }

           // composable(ScreenMenu.Profile.route) { ProfileScreen() }

            composable(ScreenMenu.About.route) { AboutScreen() }

            composable(ScreenMenu.DetailExperience.route, arguments = listOf(navArgument("id") { type = NavType.IntType })) {
                val id = it.arguments?.getInt("id") ?: 0
                DetailExperienceScreen(navController = navController, id = id)
            }

            composable(ScreenMenu.DetailClient.route, arguments = listOf(navArgument("clientId") {type = NavType.IntType})) {
                val id = it.arguments?.getInt("clientId") ?: 0
                DetailClientScreen(navController = navController, id = id)
            }

           // composable(ScreenMenu.Search.route) { SearchScreen(navController = navController) }
        }
    }
}

@Composable
private fun NavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
) {
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val items = listOf(
            BottomBarItem(
                title = stringResource(R.string.menu_experience),
                icon = Icons.Default.Home,
                screen = ScreenMenu.Experience,
            ),
           /* BottomBarItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = ScreenMenu.Home,
            ),
           BottomBarItem(
                title = stringResource(R.string.menu_chat_celebrity),
                icon = Icons.Default.Create,
                screen = ScreenMenu.Chat
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite,
                screen = ScreenMenu.Favorite
            ),
            BottomBarItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = ScreenMenu.Profile
            ), */
            BottomBarItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Default.LocationOn,
                screen = ScreenMenu.About
            )
        )

        NavigationBar {
            items.map { item ->
                NavigationBarItem(
                    selected = currentRoute == item.screen.route,
                    label = { Text(item.title)},
                    icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PemetaCardPreview() {
    PemetaCardTheme {
        PemetaCard()
    }
}