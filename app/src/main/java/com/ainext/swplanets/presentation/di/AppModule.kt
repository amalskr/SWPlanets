package com.ainext.swplanets.presentation.di

import com.ainext.swplanets.data.repository.PlanetRepository
import com.ainext.swplanets.data.repository.PlanetRepositoryImpl
import com.ainext.swplanets.data.service.PlanetApiService
import com.ainext.swplanets.presentation.planetlist.PlanetListViewModel
import com.ainext.swplanets.utils.NetworkObserver
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { NetworkObserver }

    // JSON config
    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
            prettyPrint = true
        }
    }

    // Ktor Client
    single {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(get<Json>())
            }
        }
    }

    // API Service
    single { PlanetApiService(get()) }

    // Repository
    single<PlanetRepository> { PlanetRepositoryImpl(get()) }

    viewModel { PlanetListViewModel(get()) }
}