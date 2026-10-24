package com.pemeta.pemetacard.ui.screen
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pemeta.pemetacard.navigation.ScreenMenu
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kyant.backdrop.backdrops.rememberLayerBackdrop
import com.pemeta.pemetacard.liquidglass.main.BottomBarLiquidGlass
import com.pemeta.pemetacard.ui.screen.about.AboutScreen
import com.pemeta.pemetacard.ui.screen.client.detail.DetailClientScreen
import com.pemeta.pemetacard.ui.screen.client.list.ClientListScreen
import com.pemeta.pemetacard.ui.screen.experience.detail.DetailExperienceScreen
import com.pemeta.pemetacard.ui.screen.experience.list.ListExperienceScreen
import com.pemeta.pemetacard.ui.screen.experience.ongoing.OnGoingScreen
import com.pemeta.pemetacard.ui.screen.experience.service.ExperienceByServiceScreen
import com.pemeta.pemetacard.ui.screen.experience.typeJob.ExperienceByTypeScreen
import com.pemeta.pemetacard.ui.screen.info.InfoPemetaScreen
import com.pemeta.pemetacard.ui.screen.info.contact.ContactUs
import com.pemeta.pemetacard.ui.screen.info.detail.DetailNewsInfoScreen
import com.pemeta.pemetacard.ui.screen.legal.detail.DetailLegalitasScreen
import com.pemeta.pemetacard.ui.screen.legal.list.ListLegalitasPemetaScreen
import com.pemeta.pemetacard.ui.screen.main.PemetaCardNavigationWrapperUI
import com.pemeta.pemetacard.ui.screen.team.TeamScreen
import com.pemeta.pemetacard.ui.screen.team.detail.DetailTeamScreen
import com.pemeta.pemetacard.ui.theme.PemetaCardTheme

@PreviewScreenSizes
@Composable
fun PemetaCard(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val homeListState = rememberLazyListState()
    val backdrop = rememberLayerBackdrop()

    Scaffold(
        bottomBar = {
            val hideBottomBarRoutes = listOf(
                ScreenMenu.DetailExperience.route,
                ScreenMenu.DetailClient.route,
                ScreenMenu.DetailExperienceClient.route,
                ScreenMenu.DetailLegalitas.route,
                ScreenMenu.Contact.route,
                ScreenMenu.DetailInfo.route,
                ScreenMenu.DetailTeam.route,
                ScreenMenu.DetailType.route,
                ScreenMenu.DetailService.route
            )
            if (currentRoute !in hideBottomBarRoutes) {
              //  PemetaCardNavigationWrapperUI(navController, homeListState)
                BottomBarLiquidGlass(
                    navController = navController,
                    homeListState = homeListState,
                    backdrop = backdrop,
                )
            }
        },
        modifier = modifier
    ) { paddingValues ->

        NavHost(navController = navController, startDestination = ScreenMenu.Info.route) {

            composable(ScreenMenu.Info.route) {
                InfoPemetaScreen(
                    navController = navController,
                    paddingValues = paddingValues,
                    lazyListState = homeListState
                )
            }

            composable(ScreenMenu.Experience.route) {
                ListExperienceScreen(
                    navController = navController,
                    paddingValues = paddingValues,
                )
            }

            composable(ScreenMenu.OnGoingProject.route) {
                OnGoingScreen(
                    navController = navController,
                    paddingValues = paddingValues
                )
            }

            composable(ScreenMenu.Client.route) {
                ClientListScreen(
                    navController = navController,
                    paddingValues = paddingValues,
                )
            }

            composable(ScreenMenu.About.route) {
                AboutScreen()
            }

            composable(ScreenMenu.Team.route) {
                TeamScreen(
                    navController = navController,
                    paddingValues = paddingValues
                )
            }

            composable(ScreenMenu.LegalitasPemeta.route) {
                ListLegalitasPemetaScreen(
                    navController = navController,
                    paddingValues = paddingValues
                )
            }

            composable(ScreenMenu.Contact.route) {
                ContactUs(
                    navController = navController
                )
            }

            composable(
                ScreenMenu.DetailExperience.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("id") ?: 0

                DetailExperienceScreen(
                    navController = navController,
                    id = id
                )
            }

            composable(
                ScreenMenu.DetailClient.route,
                arguments = listOf(navArgument("clientId") {type = NavType.IntType})
            ) {
                val clientId = it.arguments?.getInt("clientId") ?: 0
                DetailClientScreen(
                    navController = navController,
                    id = clientId,
                    paddingValues = paddingValues
                )
            }

            composable(
                ScreenMenu.DetailExperienceClient.route,
                arguments = listOf(navArgument("clientId") { type = NavType.IntType })
            ) {
                val clientId = it.arguments?.getInt("clientId") ?: 0
                DetailExperienceScreen(
                    navController = navController,
                    id = clientId
                )
            }

            composable(
                ScreenMenu.DetailLegalitas.route,
                arguments = listOf(navArgument("legalitasId") { type = NavType.IntType })
            ) {
                val legalitasId = it.arguments?.getInt("legalitasId") ?: 0
                DetailLegalitasScreen(
                    navController = navController,
                    id = legalitasId
                )
            }

            composable(
                ScreenMenu.DetailInfo.route,
                arguments = listOf(navArgument("infoId") { type = NavType.IntType })
            ) {
                val infoId = it.arguments?.getInt("infoId") ?: 0
                DetailNewsInfoScreen(
                    navController = navController,
                    id = infoId
                )
            }

           // composable(ScreenMenu.Search.route) { SearchScreen(navController = navController) }

            composable(
                ScreenMenu.DetailType.route,
                arguments = listOf(navArgument("typeId") {type = NavType.IntType})
            ) {
                val typeId = it.arguments?.getInt("typeId") ?: 0
                ExperienceByTypeScreen(
                    navController = navController,
                    id = typeId,
                    paddingValues = paddingValues
                )
            }

            composable(
                ScreenMenu.DetailExperienceType.route,
                arguments = listOf(navArgument("typeId") { type = NavType.IntType })
            ) {
                val typeId = it.arguments?.getInt("typeId") ?: 0
                DetailExperienceScreen(
                    navController = navController,
                    id = typeId
                )
            }

            composable(
                ScreenMenu.DetailTeam.route,
                arguments = listOf(navArgument("teamId") { type = NavType.IntType })
            ) {
                val teamId = it.arguments?.getInt("teamId") ?: 0
                DetailTeamScreen(
                    navController = navController,
                    id = teamId
                )
            }

            composable(
                ScreenMenu.DetailService.route,
                arguments = listOf(navArgument("serviceId") { type = NavType.IntType })
            ) {
                val serviceId = it.arguments?.getInt("serviceId") ?: 0
                ExperienceByServiceScreen(
                    navController = navController,
                    id = serviceId,
                    paddingValues = paddingValues
                )
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun PemetaCardPreview() {
    PemetaCardTheme {
        PemetaCard()
    }
} */