package com.ainext.swplanets.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ainext.swplanets.domain.Planet

@Dao
interface PlanetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePlanets(planets: List<Planet>)

    @Query("SELECT * FROM planets")
    suspend fun getAllPlanets(): List<Planet>

    @Query("DELETE FROM planets")
    suspend fun deleteAllPlanets()
}
