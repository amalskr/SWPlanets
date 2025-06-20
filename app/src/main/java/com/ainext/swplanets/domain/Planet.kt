package com.ainext.swplanets.domain

@kotlinx.serialization.Serializable
data class Planet(
    val name: String,
    val climate: String,
    val gravity: String,
    val orbitalPeriod: String
)
