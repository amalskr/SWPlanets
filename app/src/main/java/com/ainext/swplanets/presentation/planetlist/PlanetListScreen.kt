package com.ainext.swplanets.presentation.planetlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.ainext.swplanets.domain.Planet
import com.ainext.swplanets.ui.theme.SWPlanetsTheme
import org.koin.compose.koinInject

@Composable
fun PlanetListScreen(
    navController: NavController,
    listVm: PlanetListViewModel = koinInject()
) {

    val uiState by listVm.planetUiState.collectAsState()

    //Load MainData
    LaunchedEffect(key1 = "unit") {
        listVm.onLoadPlanetList()
    }

    when (uiState) {
        is PlanetUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is PlanetUiState.Success -> {
            val planets = (uiState as PlanetUiState.Success).planets
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(planets) { planet ->
                    PlanetCard(planet = planet)
                }
            }
        }

        is PlanetUiState.Error -> {
            val message = (uiState as PlanetUiState.Error).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = message, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Composable
fun PlanetCard(planet: Planet) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter("https://picsum.photos/100?random=${planet.name}"),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 12.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(planet.name, style = MaterialTheme.typography.titleMedium)
                Text("Climate: ${planet.climate}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    SWPlanetsTheme {
        PlanetListScreen(navController = rememberNavController())
    }
}
