package com.ainext.swplanets.presentation.planetlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainext.swplanets.data.db.DBRepository
import com.ainext.swplanets.data.repository.PlanetRepository
import com.ainext.swplanets.utils.NetworkObserver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PlanetListViewModel(
    private val networkObs: NetworkObserver,
    private val planetRepo: PlanetRepository,
    private val dbRepo: DBRepository,
) : ViewModel() {

    val planetUiState = MutableStateFlow<PlanetUiState>(PlanetUiState.Loading)

    fun onLoadPlanetList() {
        viewModelScope.launch {
            if (networkObs.isNetworkAvailable()) {
                try {
                    planetUiState.value = PlanetUiState.Loading
                    val planetsList = planetRepo.getPlanets()

                    if (planetsList.isNotEmpty()) {
                        dbRepo.savePlanets(planetsList)
                        planetUiState.value = PlanetUiState.Success(planetsList)
                    } else {
                        planetUiState.value = PlanetUiState.Error("No planets found")
                    }
                } catch (e: Exception) {
                    planetUiState.value = PlanetUiState.Error(e.message ?: "Unknown error")
                }
            } else {
                planetUiState.value = PlanetUiState.Error("No Internet Connection")
            }
        }
    }
}