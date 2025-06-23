package com.ainext.swplanets

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.ainext.swplanets.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.lang.ref.WeakReference

class SwPlanetsApp : Application() {

    companion object {
        private var _mActivity: WeakReference<Activity>? = null
        val mActivity: Activity?
            get() = _mActivity?.get()

        private lateinit var instance: SwPlanetsApp

        val context: Context
            get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidContext(this@SwPlanetsApp)
            modules(appModules)
        }

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityResumed(activity: Activity) {
                _mActivity = WeakReference(activity)
            }

            override fun onActivityPaused(activity: Activity) {
                // clear the reference when activity is no longer foreground
                if (_mActivity?.get() == activity) {
                    _mActivity?.clear()
                }
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }
}