package com.pemeta.pemetacard.navigation

sealed class ScreenMenu(val route: String) {
    object Home: ScreenMenu("Home")
    object Favorite: ScreenMenu("Favorite")
    object Search : ScreenMenu("search")
    object Chat: ScreenMenu("Chat")
    object Partner: ScreenMenu("Partner")
    object Experience: ScreenMenu("Experience")
    object OnGoingProject: ScreenMenu("OnGoingProject")
    object Profile: ScreenMenu("Profile")
    object About: ScreenMenu("About")
    object Others: ScreenMenu("Others")
    object DetailExperience: ScreenMenu("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
    object DetailClient: ScreenMenu("detailClient/{clientId}") {
        fun createRoute(clientId: Int) = "detailClient/$clientId"
    }
   // object DetailClient: ScreenMenu("detail/{id}") { fun createRoute(id: Int) = "detail/$id"}
  //  object Detail: ScreenMenu("detail/{id}") { fun createRoute(id: Int) = "detail/$id" }
}