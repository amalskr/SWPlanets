package com.ainext.swplanets.data.repository

import com.ainext.swplanets.data.service.PlanetApiService
import com.ainext.swplanets.domain.Planet

class PlanetRepositoryImpl(private val service: PlanetApiService) : PlanetRepository {
    override suspend fun getPlanets(): List<Planet> {
        return service.getPlanets().results.map {
            Planet(
                name = it.name,
                climate = it.climate,
                gravity = it.gravity,
                orbitalPeriod = it.orbitalPeriod
            )
        }
    }
}