package com.birdyteam.birdyandroidversion.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.birdyteam.birdyandroidversion.App
import com.birdyteam.birdyandroidversion.network.api.AppRequests
import com.birdyteam.birdyandroidversion.view.RegisterView
import javax.inject.Inject

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 08.07.2019
 */
@InjectViewState
class RegisterPresenter : MvpPresenter<RegisterView>() {
    @Inject
    lateinit var appRequests: AppRequests

    init {
        App.appComponent.inject(this@RegisterPresenter)
    }
}