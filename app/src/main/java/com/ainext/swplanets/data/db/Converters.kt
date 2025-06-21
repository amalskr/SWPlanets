package com.ainext.swplanets.data.db

import androidx.room.TypeConverter
import com.ainext.swplanets.domain.Planet
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    // Serialize Product to a JSON string
    @TypeConverter
    fun fromProduct(planet: Planet?): String? {
        return planet?.let { Json.encodeToString(it) }
    }

    // Deserialize JSON string back to Planet
    @TypeConverter
    fun toProduct(jsonStr: String?): Planet? {
        return jsonStr?.let { Json.decodeFromString(it) }
    }
}
