package com.ainext.swplanets.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ainext.swplanets.domain.Planet
import com.ainext.swplanets.presentation.details.PlanetDetailScreen
import com.ainext.swplanets.presentation.planetlist.PlanetListScreen
import com.ainext.swplanets.presentation.splash.SplashScreen
import com.ainext.swplanets.ui.theme.SWPlanetsTheme
import kotlinx.serialization.json.Json
import java.net.URLDecoder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SWPlanetsTheme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.PlanetList.route) {
            PlanetListScreen(navController)
        }

        composable(
            Screen.PlanetDetails.route + "/{planetJson}",
            arguments = listOf(navArgument("planetJson") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val json = backStackEntry.arguments?.getString("planetJson")
            val planet = json?.let { Json.decodeFromString<Planet>(URLDecoder.decode(it, "UTF-8")) }
            planet?.let { PlanetDetailScreen(navController, it) }
        }
    }
}