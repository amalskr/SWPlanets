package com.ainext.swplanets.data.service

import com.ainext.swplanets.data.model.PlanetResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetApiService {
    @GET("planets/")
    suspend fun getPlanets(@Query("page") page: Int = 1): PlanetResponseDto
}