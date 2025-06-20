package com.ainext.swplanets.domain

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Planet(
    val name: String,
    val climate: String,
    val gravity: String,
    @SerialName("orbital_period")
    val orbitalPeriod: String
)
