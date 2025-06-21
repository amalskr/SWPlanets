package com.ainext.swplanets.data.db

import com.ainext.swplanets.domain.Planet

class DBRepository(private val planetsDao: PlanetsDao) {

    suspend fun savePlanets(planets: List<Planet>) {
        planetsDao.deleteAllPlanets()
        planetsDao.savePlanets(planets)
    }

    suspend fun getAllPlanets(): List<Planet> {
        return planetsDao.getAllPlanets()
    }
}
