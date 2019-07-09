package com.birdyteam.birdyandroidversion.presentation.auth.signup.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.presentation.auth.signup.presenter.RegisterPresenter
import com.birdyteam.birdyandroidversion.presentation.auth.signup.view.RegisterView

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */
class RegisterActivity : MvpAppCompatActivity(),
    RegisterView {
    companion object {
        fun getInstance(packageContext: Context) = Intent(packageContext, RegisterActivity::class.java)
    }

    @InjectPresenter
    lateinit var registerPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()


    }

    /**
     * @interface : RegisterView implementation
     */

    override fun registerClicked() {

    }
}