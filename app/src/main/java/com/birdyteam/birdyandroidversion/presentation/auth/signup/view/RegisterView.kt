package com.birdyteam.birdyandroidversion.presentation.auth.signup.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleTagStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 08.07.2019
 */
@StateStrategyType(OneExecutionStateStrategy::class)
interface RegisterView : MvpView {

    companion object {
        private const val LOAD_STATE = "load.state"
    }

    @StateStrategyType(value = AddToEndSingleTagStrategy::class, tag = LOAD_STATE)
    fun showLoad()

    @StateStrategyType(value = AddToEndSingleTagStrategy::class, tag = LOAD_STATE)
    fun hideLoad()

    fun registrationCompleted()

    fun showError(message: String)
}