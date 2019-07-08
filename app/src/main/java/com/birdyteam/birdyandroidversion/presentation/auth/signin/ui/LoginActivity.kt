package com.birdyteam.birdyandroidversion.presentation.auth.signin.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.birdyteam.birdyandroidversion.App
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.presentation.common.LoadingFragment
import com.birdyteam.birdyandroidversion.presentation.auth.signin.presenter.LoginPresenter
import com.birdyteam.birdyandroidversion.user.CurrentUser
import com.birdyteam.birdyandroidversion.presentation.auth.signin.view.LoginView

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */
class LoginActivity : MvpAppCompatActivity(),
    LoginView {

    companion object {
        private const val DIALOG_TAG = "loading.fragment.tag"

        @Suppress("unused")
        fun getInstance(packageContext: Context) = Intent(packageContext, LoginActivity::class.java)
    }

    private val tag = LoginActivity::class.java.simpleName
    private lateinit var dialogFragment: LoadingFragment
    private lateinit var loginBtn: Button
    private lateinit var registerBtn: Button
    private lateinit var email: EditText
    private lateinit var password: EditText

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    @ProvidePresenter
    fun providePresenter(): LoginPresenter = LoginPresenter(
        getSharedPreferences(
            CurrentUser.USER, Context.MODE_PRIVATE
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        App.appComponent.inject(this@LoginActivity)
        supportActionBar?.hide()

        initWidgets()

    }

    private fun initWidgets() {
        dialogFragment = LoadingFragment()
        loginBtn = findViewById(R.id.login_btn)
        loginBtn.setOnClickListener {
            loginPresenter.signInClicked(
                email.text.toString(),
                password.text.toString()
            )
        }

        registerBtn = findViewById(R.id.register_btn)
        registerBtn.setOnClickListener {
            loginPresenter.signUpClicked()
        }
        email = findViewById(R.id.entered_login)
        password = findViewById(R.id.entered_password)
    }

    /**
     * @interface : LoginView implementation
     */

    override fun hideLoad() {
        dialogFragment.dismiss()
    }

    override fun showError(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoad() {
        dialogFragment.show(supportFragmentManager,
            DIALOG_TAG
        )
    }

    override fun signIn() {
        Log.d(tag, "Successfully logged in!")
    }

    override fun signUp() {

    }
}