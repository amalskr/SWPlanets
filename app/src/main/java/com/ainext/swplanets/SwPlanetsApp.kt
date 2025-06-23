package com.ainext.swplanets

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.ainext.swplanets.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SwPlanetsApp : Application() {

    companion object {
        lateinit var currentActivity: Activity
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

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityResumed(activity: Activity) {
                currentActivity = activity
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }
}