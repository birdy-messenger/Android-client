package com.birdyteam.birdyandroidversion.presentation.auth.signup.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.birdyteam.birdyandroidversion.App
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.presentation.auth.signin.ui.LoginActivity
import com.birdyteam.birdyandroidversion.presentation.auth.signup.presenter.RegisterPresenter
import com.birdyteam.birdyandroidversion.presentation.auth.signup.view.RegisterView
import com.birdyteam.birdyandroidversion.presentation.common.LoadingFragment

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */
class RegisterActivity : MvpAppCompatActivity(),
    RegisterView {
    companion object {
        private const val LOADING_TAG = "loading.tag"

        fun getInstance(packageContext: Context) = Intent(packageContext, RegisterActivity::class.java)
    }

    @InjectPresenter
    lateinit var registerPresenter: RegisterPresenter

    @ProvidePresenter
    fun providePresenter(): RegisterPresenter = App.appComponent
        .presenter()
        .registerPresenter()

    private lateinit var userTag: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var confirm: EditText
    private lateinit var registerBtn: Button
    private lateinit var dialogFragment: LoadingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        initViews()
    }

    private fun initViews() {
        dialogFragment = LoadingFragment().apply { isCancelable = false }
        registerBtn = findViewById<Button>(R.id.register_btn).apply {
            setOnClickListener {
                registerPresenter.registerClicked(
                    userTag.text.toString(),
                    email.text.toString(),
                    password.text.toString(),
                    confirm.text.toString()
                )
            }
        }
        userTag = findViewById(R.id.enter_name)
        email = findViewById(R.id.enter_email)
        password = findViewById(R.id.enter_password)
        confirm = findViewById(R.id.confirm_password)
    }

    override fun onBackPressed() {
        startActivity(
            LoginActivity.getInstance(this)
        )
    }

    /**
     * @interface : RegisterView implementation
     */

    override fun hideLoad() {
        dialogFragment.dismiss()
    }

    override fun registrationCompleted() {

    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoad() {
        dialogFragment.show(supportFragmentManager, LOADING_TAG)
    }
}