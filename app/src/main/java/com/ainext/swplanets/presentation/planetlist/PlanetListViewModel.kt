package com.ainext.swplanets.presentation.planetlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainext.swplanets.data.repository.PlanetRepository
import com.ainext.swplanets.utils.NetworkObserver
import kotlinx.coroutines.launch

class PlanetListViewModel(
    private val networkObs: NetworkObserver,
    private val planetRepo: PlanetRepository
) : ViewModel() {

    fun onLoadPlanetList() {
        viewModelScope.launch {
            if (networkObs.isNetworkAvailable()) {
                try {
                    val planets = planetRepo.getPlanets()
                    println("Planets fetched: ${planets.size}")
                } catch (e: Exception) {
                    println("Error fetching planets: ${e.message}")
                }
            } else {
                println("No network available")
            }
        }
    }
}