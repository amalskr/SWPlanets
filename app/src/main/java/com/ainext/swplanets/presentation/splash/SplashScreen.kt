package com.ainext.swplanets.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ainext.swplanets.presentation.Screen
import com.ainext.swplanets.ui.theme.SWPlanetsTheme
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@Composable
fun SplashScreen(
    navController: NavController,
    splashVm: SplashViewModel = koinInject()
) {

    LaunchedEffect(Unit) {
        delay(2000)
        splashVm.onLoad()
        navController.navigate(Screen.PlanetList.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Star Wars Planets",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    SWPlanetsTheme {
        SplashScreen(navController = rememberNavController())
    }
}
