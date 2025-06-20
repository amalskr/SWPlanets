package com.ainext.swplanets.presentation

import com.ainext.swplanets.presentation.splash.SplashViewModel
import com.ainext.swplanets.utils.NetworkObserver
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Json { isLenient = true; ignoreUnknownKeys = true } }
    single { NetworkObserver }

    viewModel { SplashViewModel(get()) }
}