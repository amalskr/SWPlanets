package com.ainext.swplanets.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
@Serializable
data class Planet(
    val name: String,
    val climate: String,
    val gravity: String,
    @SerialName("orbital_period")
    val orbitalPeriod: String
)
*/

@Serializable
@Entity(tableName = "planets")
data class Planet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String? = null,
    val climate:  String? = null,
    val gravity:  String? = null,
    @SerialName("orbital_period") val orbitalPeriod:  String? = null
)