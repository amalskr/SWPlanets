package com.ainext.swplanets.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ainext.swplanets.domain.Planet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailScreen(planet: Planet) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(planet.name) })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Climate: ${planet.climate}", style = MaterialTheme.typography.bodyLarge)
            Text("Gravity: ${planet.gravity}", style = MaterialTheme.typography.bodyMedium)
            Text(
                "Orbital Period: ${planet.orbitalPeriod}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}