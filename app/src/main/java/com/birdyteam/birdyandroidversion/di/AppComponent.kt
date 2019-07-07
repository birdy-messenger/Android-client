package com.birdyteam.birdyandroidversion.di

import com.birdyteam.birdyandroidversion.activity.LoginActivity
import com.birdyteam.birdyandroidversion.di.modules.LoadingModule
import com.birdyteam.birdyandroidversion.di.modules.NetworkModule
import com.birdyteam.birdyandroidversion.presenter.LoginPresenter
import dagger.Component
import javax.inject.Singleton

@Component (modules = [LoadingModule::class, NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(activity : LoginActivity)
    fun inject(presenter: LoginPresenter)
}
