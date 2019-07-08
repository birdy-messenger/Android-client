package com.birdyteam.birdyandroidversion.di

import com.birdyteam.birdyandroidversion.presentation.auth.signin.ui.LoginActivity
import com.birdyteam.birdyandroidversion.di.modules.NetworkModule
import com.birdyteam.birdyandroidversion.presentation.auth.signin.presenter.LoginPresenter
import com.birdyteam.birdyandroidversion.presentation.auth.ui.presenter.RegisterPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: LoginActivity)
    fun inject(presenter: LoginPresenter)
    fun inject(presenter: RegisterPresenter)
}
