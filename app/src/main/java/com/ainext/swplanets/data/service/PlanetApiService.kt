package com.ainext.swplanets.data.service

import com.ainext.swplanets.data.model.PlanetResponseDto
import com.ainext.swplanets.domain.Planet
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PlanetApiService {
    @GET("planets/")
    suspend fun getPlanets(@Query("page") page: Int = 1): PlanetResponseDto

    // POST: Create a new planet
    @POST("planets/")
    suspend fun createPlanet(@Body planet: Planet): Planet

    // PUT: Update existing planet by ID
    @PUT("planets/{id}/")
    suspend fun updatePlanet(
        @Path("id") id: Int,
        @Body planet: Planet
    ): Planet

    // DELETE: Delete a planet by ID
    @DELETE("planets/{id}/")
    suspend fun deletePlanet(@Path("id") id: Int): Unit
}