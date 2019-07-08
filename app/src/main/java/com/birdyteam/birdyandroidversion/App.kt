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
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .build()
    }

}