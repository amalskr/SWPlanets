package com.ainext.swplanets.presentation

sealed class Screen(val route: String, val label: String) {
    data object Splash : Screen("splash", "Splash")
    data object PlanetList : Screen("planet_list", "Planets")
    data object PlanetDetails : Screen("planet_details", "Details")
}