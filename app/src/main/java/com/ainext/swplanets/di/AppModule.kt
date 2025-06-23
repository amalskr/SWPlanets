package com.ainext.swplanets.di

import androidx.room.Room
import com.ainext.swplanets.data.common.NetworkConstants.BASE_URL
import com.ainext.swplanets.data.common.UnsafeOkHttpClient
import com.ainext.swplanets.data.db.DBRepository
import com.ainext.swplanets.data.db.PlanetsDatabase
import com.ainext.swplanets.data.repository.PlanetRepository
import com.ainext.swplanets.data.repository.PlanetRepositoryImpl
import com.ainext.swplanets.data.service.PlanetApiService
import com.ainext.swplanets.presentation.planetlist.PlanetListViewModel
import com.ainext.swplanets.utils.NetworkObserver
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit

val appModule = module {
    single { NetworkObserver }

    //Json Convertor
    single<Converter.Factory> {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
        }.asConverterFactory("application/json".toMediaType())
    }

    //this is unsafe call to prevent ssl issue in base api
    single {
        UnsafeOkHttpClient.create()
    }

    // Retrofit Instance
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get())
            .build()
            .create(PlanetApiService::class.java)
    }

    //Repository Instance
    single<PlanetRepository> { PlanetRepositoryImpl(get()) }

    // Room Database
    single {
        Room.databaseBuilder(get(), PlanetsDatabase::class.java, "planets_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    // DAO
    single { get<PlanetsDatabase>().planetsDao() }

    // Product Repository
    single { DBRepository(get()) }

    viewModel { PlanetListViewModel(get(), get(), get()) }
}