package com.ainext.swplanets.data.repository

import com.ainext.swplanets.domain.Planet

interface PlanetRepository {
    suspend fun getPlanets(): List<Planet>
}