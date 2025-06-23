package com.ainext.swplanets.di

import androidx.room.Room
import com.ainext.swplanets.data.db.DBRepository
import com.ainext.swplanets.data.db.PlanetsDatabase
import org.koin.dsl.module

private const val DATABASE_NAME = "planets_database"

val databaseModule = module {

    single {
        Room.databaseBuilder(get(), PlanetsDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<PlanetsDatabase>().planetsDao() }

    single { DBRepository(get()) }
}