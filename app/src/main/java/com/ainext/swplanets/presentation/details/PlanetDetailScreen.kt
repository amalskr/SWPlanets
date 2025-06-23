package com.ainext.swplanets.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ainext.swplanets.data.common.NetworkConstants.IMAGE_URL
import com.ainext.swplanets.domain.Planet
import com.ainext.swplanets.ui.components.SpacerUp
import com.ainext.swplanets.utils.extensions.capitalizeFirst

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetDetailScreen(navController: NavController, planet: Planet) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    planet.name?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            val imageUrl = IMAGE_URL + planet.name

            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            SpacerUp(size = 16.dp)

            DescriptionView(planet)
        }
    }
}

@Composable
fun DescriptionView(planet: Planet) {

    Column(modifier = Modifier.fillMaxWidth()) {
        ClimateGravityRow(planet.climate, planet.gravity)

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            "Orbital Period : ${planet.orbitalPeriod}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun ClimateGravityRow(climate: String?, gravity: String?) {
    /*Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text("Climate".uppercase(), style = MaterialTheme.typography.labelMedium)
            Text(climate.orEmpty().capitalizeFirst(), style = MaterialTheme.typography.bodyLarge)
        }

        Column(horizontalAlignment = Alignment.End) {
            Text("Gravity".uppercase(), style = MaterialTheme.typography.labelMedium)
            Text(gravity.orEmpty().capitalizeFirst(), style = MaterialTheme.typography.bodyMedium)
        }
    }*/

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                .padding(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Climate".uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    climate.orEmpty().capitalizeFirst(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
                .padding(8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Gravity".uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    gravity.orEmpty().capitalizeFirst(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
