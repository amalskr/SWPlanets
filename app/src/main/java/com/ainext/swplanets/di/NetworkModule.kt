package com.ainext.swplanets.di

import com.ainext.swplanets.data.common.NetworkConstants
import com.ainext.swplanets.data.common.UnsafeOkHttpClient
import com.ainext.swplanets.data.service.PlanetApiService
import com.ainext.swplanets.utils.NetworkObserver
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit

private val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
}

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single { NetworkObserver }

    single<Converter.Factory> {
        json.asConverterFactory("application/json".toMediaType())
    }

    single { UnsafeOkHttpClient.create() }

    single<PlanetApiService> {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
            .create(PlanetApiService::class.java)
    }
}