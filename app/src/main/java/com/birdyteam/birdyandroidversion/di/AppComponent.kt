package com.birdyteam.birdyandroidversion.di

import com.birdyteam.birdyandroidversion.di.modules.AppModule
import com.birdyteam.birdyandroidversion.di.modules.LoginModule
import com.birdyteam.birdyandroidversion.di.modules.NetworkModule
import com.birdyteam.birdyandroidversion.di.modules.RegisterModule
import com.birdyteam.birdyandroidversion.presentation.auth.signin.presenter.LoginPresenter
import com.birdyteam.birdyandroidversion.presentation.auth.signin.ui.LoginActivity
import com.birdyteam.birdyandroidversion.presentation.auth.signup.presenter.RegisterPresenter
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class, LoginModule::class, RegisterModule::class])
@Singleton
interface AppComponent {
    fun inject(activity: LoginActivity)
    fun inject(presenter: LoginPresenter)
    fun inject(presenter: RegisterPresenter)
    fun presenter(): PresenterComponent

    @Subcomponent
    interface PresenterComponent {
        fun loginPresenter(): LoginPresenter

        fun registerPresenter(): RegisterPresenter
    }
}
