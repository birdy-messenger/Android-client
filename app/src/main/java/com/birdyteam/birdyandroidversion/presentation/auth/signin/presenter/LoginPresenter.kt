package com.birdyteam.birdyandroidversion.presentation.auth.signin.presenter

import android.content.SharedPreferences
import android.content.res.Resources
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.birdyteam.birdyandroidversion.App
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.domain.input.LoginInput
import com.birdyteam.birdyandroidversion.domain.interactor.SignInInteractor
import com.birdyteam.birdyandroidversion.domain.repository.UserAuthInfoRepositoryImpl
import com.birdyteam.birdyandroidversion.domain.validation.ValidateLoginInput
import com.birdyteam.birdyandroidversion.domain.validation.ValidationError
import com.birdyteam.birdyandroidversion.domain.validation.ValidationErrorState
import com.birdyteam.birdyandroidversion.domain.validation.ValidationSuccess
import com.birdyteam.birdyandroidversion.presentation.auth.signin.view.LoginView
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import java.lang.StringBuilder
import javax.inject.Inject

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */
@InjectViewState
class LoginPresenter @Inject constructor(
    resources: Resources,
    private val preferences: SharedPreferences,
    private val validateLoginInput: ValidateLoginInput,
    private val signInInteractor: SignInInteractor
) : MvpPresenter<LoginView>() {

    private val tag = LoginPresenter::class.java.simpleName
    private var authRequest: Disposable? = null
    private val errors = resources.getStringArray(R.array.validation_errors)

    //Load preferences(!!!)
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
        val id = preferences.getInt(UserAuthInfoRepositoryImpl.SAVE_ID, -1)
        val token = preferences.getLong(UserAuthInfoRepositoryImpl.SAVE_TOKEN, -1L)
        return !(id == -1 || token == -1L)
    }

    fun signInClicked(email: String, password: String) {
        if (authRequest?.isDisposed == false)
            return
        when (val result = validateLoginInput.validate(LoginInput(email, password))) {
            is ValidationError -> showValidationError(result)
            is ValidationSuccess -> {
                viewState.showLoad()
                signInInteractor.signIn(LoginInput(email, password))
                    .subscribe({
                        viewState.hideLoad()
                        viewState.signIn()
                    }, {
                        viewState.hideLoad()
                        val message = (it as HttpException).response()
                        Log.e(tag, message.toString())
                    })
            }
        }
    }

    private fun showValidationError(result: ValidationError) = result.apply {
        val message = StringBuilder()
        if (emailErrorMessage != null)
            message.append(errors[0])
        Thread.sleep(500)
        message.append(when (passwordErrorMessage?.errorMessage) {
            ValidationErrorState.TOO_SHORT -> " ${errors[1]}"
            ValidationErrorState.TOO_LONG -> " ${errors[3]}"
            ValidationErrorState.NOT_MATCH_PATTERN -> " ${errors[2]}"
            else -> ""
        })
        viewState.showError(message.toString())
    }

    fun signUpClicked() {

    }
}