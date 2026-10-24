package com.pemeta.pemetacard.navigation

sealed class ScreenMenu(val route: String) {
    object Home: ScreenMenu("Home")
    object Favorite: ScreenMenu("Favorite")
    object Search : ScreenMenu("search")
    object Chat: ScreenMenu("Chat")
    object Partner: ScreenMenu("Partner")
    object Experience: ScreenMenu("Experience")
    object OnGoingProject: ScreenMenu("OnGoingProject")
    object Client: ScreenMenu("Client")
    object Profile: ScreenMenu("Profile")
    object Team: ScreenMenu("Team")
    object About: ScreenMenu("About")
    object Info: ScreenMenu("Info")
    object Contact: ScreenMenu("ContactUs")
    object LegalitasPemeta: ScreenMenu("Legalitas")
    object Others: ScreenMenu("Others")

    object DetailExperience: ScreenMenu("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
    object DetailClient: ScreenMenu("detailClient/{clientId}") {
        fun createRoute(clientId: Int) = "detailClient/$clientId"
    }
    object DetailExperienceClient: ScreenMenu("detail/detailClient/{clientId}") {
        fun createRoute(clientId: Int) = "detail/detailClient/$clientId"
    }
    object DetailInfo: ScreenMenu("detailInfo/{infoId}") {
        fun createRoute(infoId: Int) = "detailInfo/$infoId"
    }
    object DetailLegalitas: ScreenMenu("detailLegalitas/{legalitasId}") {
        fun createRoute(legalitasId: Int) = "detailLegalitas/$legalitasId"
    }
    object DetailType: ScreenMenu("detailType/{typeId}") {
        fun createRoute(typeId: Int) = "detailType/$typeId"
    }

    object DetailExperienceType: ScreenMenu("detail/detailType/{typeId}") {
        fun createRoute(typeId: Int) = "detail/detailType/$typeId"
    }

    object DetailService: ScreenMenu("detailService/{serviceId}") {
        fun createRoute(serviceId: Int) = "detailService/$serviceId"
    }

    object DetailTeam: ScreenMenu("detailTeam/{teamId}") {
        fun createRoute(teamId: Int) = "detailTeam/$teamId"
    }

    object DetailPartner: ScreenMenu("detailPartner/{partnerId}") {
        fun createRoute(partnerId: Int) = "detailPartner/$partnerId"
    }

   // object DetailClient: ScreenMenu("detail/{id}") { fun createRoute(id: Int) = "detail/$id"}
  //  object Detail: ScreenMenu("detail/{id}") { fun createRoute(id: Int) = "detail/$id" }
}