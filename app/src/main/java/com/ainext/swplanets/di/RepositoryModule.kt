package com.ainext.swplanets.di

import com.ainext.swplanets.data.repository.PlanetRepository
import com.ainext.swplanets.data.repository.PlanetRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<PlanetRepository> { PlanetRepositoryImpl(get()) }
}