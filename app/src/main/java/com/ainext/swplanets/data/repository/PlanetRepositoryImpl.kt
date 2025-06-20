package com.ainext.swplanets.data.repository

import com.ainext.swplanets.data.service.PlanetApiService
import com.ainext.swplanets.domain.Planet

class PlanetRepositoryImpl(
    private val apiService: PlanetApiService
) : PlanetRepository {

    override suspend fun getPlanets(): List<Planet> {
        return try {
            val response = apiService.getPlanets()
            response.results.map {
                Planet(
                    name = it.name,
                    climate = it.climate,
                    gravity = it.gravity,
                    orbitalPeriod = it.orbitalPeriod
                )
            }
        } catch (e: Exception) {
            // You can log or handle the error here
            emptyList() // Return fallback empty list or rethrow based on your strategy
        }
    }
}