package com.birdyteam.birdyandroidversion.di

import com.birdyteam.birdyandroidversion.activity.LoginActivity
import com.birdyteam.birdyandroidversion.di.modules.NetworkModule
import com.birdyteam.birdyandroidversion.presenter.LoginPresenter
import com.birdyteam.birdyandroidversion.presenter.RegisterPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: LoginActivity)
    fun inject(presenter: LoginPresenter)
    fun inject(presenter: RegisterPresenter)
}
