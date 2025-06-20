package com.ainext.swplanets.presentation.planetlist

import com.ainext.swplanets.domain.Planet

sealed class PlanetUiState {
    data object Loading : PlanetUiState()
    data class Success(val planets: List<Planet>) : PlanetUiState()
    data class Error(val message: String) : PlanetUiState()
}