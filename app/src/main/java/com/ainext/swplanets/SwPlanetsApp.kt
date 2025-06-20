package com.ainext.swplanets

import android.app.Application
import android.content.Context

class SwPlanetsApp : Application() {

    companion object {
        private lateinit var instance: SwPlanetsApp
        val context: Context
            get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}