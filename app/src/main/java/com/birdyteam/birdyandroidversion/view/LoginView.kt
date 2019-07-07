package com.birdyteam.birdyandroidversion.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.*

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */

@StateStrategyType (value = OneExecutionStateStrategy::class)
interface LoginView : MvpView {

    companion object {
        private const val LOAD_STATE = "load.state"
    }

    @StateStrategyType (value = AddToEndSingleTagStrategy::class, tag = LOAD_STATE)
    fun showLoad()

    @StateStrategyType (value = AddToEndSingleTagStrategy::class, tag = LOAD_STATE)
    fun hideLoad()

    fun showError(message : String)

    fun signIn()

    fun signUp()
}