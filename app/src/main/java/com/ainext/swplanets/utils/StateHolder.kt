package com.ainext.swplanets.utils

import com.ainext.swplanets.domain.Planet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object StateHolder {
    private val _state = MutableStateFlow(GlobalState())
    val state: StateFlow<GlobalState> = _state

    fun updatePlanetList(planets: List<Planet>) {
        _state.value = _state.value.copy(planetList = planets)
    }

    fun clear() {
        _state.value = GlobalState()
    }
}

data class GlobalState(
    val planetList: List<Planet> = mutableListOf()
)