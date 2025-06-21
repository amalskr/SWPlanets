package com.ainext.swplanets

import android.app.Application
import android.content.Context
import com.ainext.swplanets.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SwPlanetsApp : Application() {

    companion object {
        private lateinit var instance: SwPlanetsApp
        val context: Context
            get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidContext(this@SwPlanetsApp)
            modules(appModule)
        }
    }
}