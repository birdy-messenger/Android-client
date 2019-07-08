package com.birdyteam.birdyandroidversion.presenter

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.birdyteam.birdyandroidversion.App
import com.birdyteam.birdyandroidversion.md5.createMD5
import com.birdyteam.birdyandroidversion.network.api.AppRequests
import com.birdyteam.birdyandroidversion.user.CurrentUser
import com.birdyteam.birdyandroidversion.view.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */
@InjectViewState
class LoginPresenter(private val preferences: SharedPreferences) : MvpPresenter<LoginView>() {

    companion object {
        private var TOO_LONG_PASSWORD = "Password is too long!"
        private var TOO_SHORT_PASSWORD = "Password is too short!"
        private var EMAIL_NOT_MATCH = "Email doesn't match pattern!"
        private var PASSWORD_NOT_MATCH = "Password contains illegal symbols"
        private const val EMAIL_PATTERN = "[\\w-]+@[\\w]+\\.[\\w]+"
        private const val PASSWORD_PATTERN = "[\\w]+"
    }

    @Inject
    lateinit var appRequests: AppRequests

    private val tag = LoginPresenter::class.java.simpleName
    private var authRequest: Disposable? = null

    init {
        App.appComponent.inject(this@LoginPresenter)
        if (hasSharedPreferences()) {
            Log.d(tag, "Has shared preferences!")
            viewState.signIn()
        } else {
            Log.d(tag, "Doesn't have preferences")
        }
    }

    private fun hasSharedPreferences(): Boolean {
        val id = preferences.getInt(CurrentUser.SAVE_ID, -1)
        val token = preferences.getLong(CurrentUser.SAVE_TOKEN, -1L)
        if (id == -1 || token == -1L)
            return false
        CurrentUser.id = id
        CurrentUser.token = token
        return true
    }

    fun signInClicked(email: String, password: String) {
        Log.d(tag, "Sign in Clicked with email : $email and password : $password")
        if (authRequest?.isDisposed == false)
            return
        Log.d(tag, "Checking correctness")
        if (checkCorrectness(email, password)) {
            viewState?.showLoad()
            authRequest = appRequests.auth(email, password.createMD5())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    CurrentUser.id = it.id
                    CurrentUser.token = it.token
                    savePreferences()
                    Log.d(tag, "Received id : ${it.id} and token : ${it.token}")
                    viewState.hideLoad()
                    viewState.signIn()
                }, {
                    Log.d(tag, "Error occurred : $it")
                    viewState.hideLoad()
                })
        }
    }

    private fun savePreferences() = preferences.edit {
        clear()
        this.putInt(CurrentUser.SAVE_ID, CurrentUser.id)
        this.putLong(CurrentUser.SAVE_TOKEN, CurrentUser.token)
        apply()
    }

    fun signUpClicked() {

    }

    fun checkCorrectness(email: String, password: String): Boolean =
        (checkEmail(email) && checkPassword(password))

    private fun checkEmail(email: String): Boolean {
        if (!email.matches(Regex(EMAIL_PATTERN))) {
            viewState.showError(EMAIL_NOT_MATCH)
            return false
        }
        return true
    }

    private fun checkPassword(password: String): Boolean {
        if (password.length < 6) {
            viewState.showError(TOO_SHORT_PASSWORD)
            return false
        }
        if (password.length > 32) {
            viewState.showError(TOO_LONG_PASSWORD)
            return false
        }
        if (!password.matches(Regex(PASSWORD_PATTERN))) {
            viewState.showError(PASSWORD_NOT_MATCH)
            return false
        }
        return true
    }
}