package com.ainext.swplanets.data.model

import com.ainext.swplanets.domain.Planet

@kotlinx.serialization.Serializable
data class PlanetResponseDto(
    val count: Int,
    val next: String?,
    val results: List<Planet>
)