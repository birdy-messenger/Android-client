package com.birdyteam.birdyandroidversion

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.birdyteam.birdyandroidversion.di.AppComponent
import com.birdyteam.birdyandroidversion.di.DaggerAppComponent
import com.birdyteam.birdyandroidversion.di.modules.AppModule
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

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
            .appModule(AppModule(this))
            .build()
    }

}