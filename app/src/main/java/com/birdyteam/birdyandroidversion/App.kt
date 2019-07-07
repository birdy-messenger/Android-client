package com.birdyteam.birdyandroidversion

import android.app.Application
import com.birdyteam.birdyandroidversion.di.AppComponent
import com.birdyteam.birdyandroidversion.di.DaggerAppComponent
import java.lang.IllegalStateException

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */
class App : Application() {
    companion object {
        private var mAppComponent : AppComponent? = null
        val appComponent : AppComponent
        get() {
            mAppComponent ?: throw IllegalStateException("AppComponent hasn't been initialized yet")
            return mAppComponent!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        mAppComponent = DaggerAppComponent.builder()
            .build()
    }

}