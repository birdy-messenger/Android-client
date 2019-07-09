package com.birdyteam.birdyandroidversion

import android.app.Application
import com.birdyteam.birdyandroidversion.di.AppComponent
import com.birdyteam.birdyandroidversion.di.DaggerAppComponent
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */
class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
        lateinit var cicerone: Cicerone<Router>
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        initializeCicerone()
    }

    private fun initializeCicerone() {
        cicerone = Cicerone.create()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .build()
    }

}