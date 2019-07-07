package com.birdyteam.birdyandroidversion.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.birdyteam.birdyandroidversion.App
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.fragment.LoadingFragment
import com.birdyteam.birdyandroidversion.presenter.LoginPresenter
import com.birdyteam.birdyandroidversion.view.LoginView
import javax.inject.Inject

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */
class LoginActivity : MvpAppCompatActivity(), LoginView {

    companion object {
        private const val DIALOG_TAG = "loading.fragment.tag"
    }

    @Inject
    lateinit var dialogFragment : LoadingFragment

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    private var loginBtn : Button? = null
    private var registerBtn : Button? = null

    private var email : EditText? = null
    private var password : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        App.appComponent.inject(this@LoginActivity)
        supportActionBar?.hide()

        initWidgets()

    }

    private fun initWidgets() {
        loginBtn = findViewById(R.id.login_btn)
        loginBtn?.setOnClickListener {
            loginPresenter.signInClicked(
                email?.text.toString(),
                password?.text.toString()
            )
        }

        registerBtn = findViewById(R.id.register_btn)
        registerBtn?.setOnClickListener {
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

    override fun showError(message : String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoad() {
        dialogFragment.show(supportFragmentManager, DIALOG_TAG)
    }

    override fun signIn() {

    }
}