package com.ainext.swplanets.data.service

import com.ainext.swplanets.data.model.PlanetResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class PlanetApiService(private val client: HttpClient) {
    suspend fun getPlanets(): PlanetResponseDto {
        return client.get("https://swapi.dev/api/planets/").body()
    }
}