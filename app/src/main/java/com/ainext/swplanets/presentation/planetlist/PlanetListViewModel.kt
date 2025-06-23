package com.ainext.swplanets.presentation.planetlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainext.swplanets.data.db.DBRepository
import com.ainext.swplanets.data.repository.PlanetRepository
import com.ainext.swplanets.utils.NetworkObserver
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlanetListViewModel(
    private val networkObs: NetworkObserver,
    private val planetRepo: PlanetRepository,
    private val dbRepo: DBRepository,
) : ViewModel() {

    val planetUiState = MutableStateFlow<PlanetUiState>(PlanetUiState.Loading)
    val isOnline: StateFlow<Boolean> = NetworkObserver.observeNetwork()
        .stateIn(viewModelScope, SharingStarted.Eagerly, NetworkObserver.isNetworkAvailable())

    //val fetchState = MutableStateFlow("initial")
    val toastFlow = MutableSharedFlow<String>()

    fun onLoadPlanetList() {
        viewModelScope.launch {
            if (networkObs.isNetworkAvailable()) {
                try {
                    planetUiState.value = PlanetUiState.Loading
                    val planetsList = planetRepo.getPlanets()

                    if (planetsList.isNotEmpty()) {
                        //fetchState.value = "Success"
                        toastFlow.emit("Online Data")
                        dbRepo.savePlanets(planetsList)
                        displayPlanets()
                    } else {
                        //fetchState.value = "Success,No planets found"
                        toastFlow.emit("No planets found")
                        planetUiState.value = PlanetUiState.Error("No planets found")
                    }
                } catch (e: Exception) {
                    toastFlow.emit("No planets found")
                    planetUiState.value = PlanetUiState.Error(e.message ?: "Unknown error")
                }
            } else {
                if (dbRepo.isHasOfflineData()) {
                    //fetchState.value = "Offline"
                    toastFlow.emit("Offline Data")
                    displayPlanets()
                } else {
                    //fetchState.value = "Offline,No offline data"
                    toastFlow.emit("Offline data not found")
                    planetUiState.value =
                        PlanetUiState.Error("No Internet Connection & No Offline Data")
                }
            }
        }
    }

    private fun displayPlanets() {
        viewModelScope.launch {
            val planetsList = dbRepo.getAllPlanets()
            planetUiState.value = PlanetUiState.Success(planetsList)
        }
    }
}