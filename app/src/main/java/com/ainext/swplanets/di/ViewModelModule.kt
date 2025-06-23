package com.ainext.swplanets.di

import com.ainext.swplanets.presentation.planetlist.PlanetListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PlanetListViewModel(get(), get(), get()) }
}