package com.ainext.swplanets.presentation.di

import com.ainext.swplanets.data.repository.PlanetRepository
import com.ainext.swplanets.data.repository.PlanetRepositoryImpl
import com.ainext.swplanets.data.service.PlanetApiService
import com.ainext.swplanets.presentation.planetlist.PlanetListViewModel
import com.ainext.swplanets.utils.NetworkObserver
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit

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

    //Network Logs
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    //Json Convertor
    single<Converter.Factory> {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
        }.asConverterFactory("application/json".toMediaType())
    }

    //Network Instance
    single {
        Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .client(get())
            .addConverterFactory(get())
            .build()
            .create(PlanetApiService::class.java)
    }

    //Repository Instance
    single<PlanetRepository> { PlanetRepositoryImpl(get()) }

    viewModel { PlanetListViewModel(get(),get()) }
}