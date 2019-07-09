package com.birdyteam.birdyandroidversion.presentation.auth.signup.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.birdyteam.birdyandroidversion.App
import com.birdyteam.birdyandroidversion.data.network.api.app.AuthenticationApi
import com.birdyteam.birdyandroidversion.presentation.auth.signup.view.RegisterView
import javax.inject.Inject

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 08.07.2019
 */
@InjectViewState
class RegisterPresenter : MvpPresenter<RegisterView>() {
    @Inject
    lateinit var authenticationApi: AuthenticationApi

    init {
        App.appComponent.inject(this@RegisterPresenter)
    }
}