package com.ainext.swplanets.presentation.planetlist

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
import com.ainext.swplanets.ui.theme.SWPlanetsTheme
import org.koin.compose.koinInject

@Composable
fun PlanetListScreen(
    navController: NavController,
    listVm: PlanetListViewModel = koinInject()
) {

    //Load MainData
    LaunchedEffect(key1 = "MainData") {
        listVm.onLoad()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Planet List",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    SWPlanetsTheme {
        PlanetListScreen(navController = rememberNavController())
    }
}
