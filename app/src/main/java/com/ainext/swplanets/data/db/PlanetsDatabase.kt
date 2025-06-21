package com.ainext.swplanets.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ainext.swplanets.domain.Planet

@Database(entities = [Planet::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class PlanetsDatabase : RoomDatabase() {
    abstract fun planetsDao(): PlanetsDao
}