package com.ycosilvallana.onepiece.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash_screen")
    data object Welcome : Screen("welcome_screen")
    data object Home : Screen("home_screen")
    data object Details : Screen("details_screen/{characterId}") {
        fun passCharacterId(characterId: Int): String {
            return "details_screen/$characterId"
        }
    }
    data object Search: Screen("search_screen")
}