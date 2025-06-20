package com.ainext.swplanets.presentation

import com.ainext.swplanets.presentation.splash.SplashViewModel
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Serialization
    single { Json { isLenient = true; ignoreUnknownKeys = true } }

    //ViewModels
    viewModel { SplashViewModel() }
}