package com.birdyteam.birdyandroidversion.presentation.auth.signin.presenter

import android.content.res.Resources
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.birdyteam.birdyandroidversion.App
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.domain.input.LoginInput
import com.birdyteam.birdyandroidversion.domain.interactor.CheckAuthorizedInteractor
import com.birdyteam.birdyandroidversion.domain.interactor.SignInInteractor
import com.birdyteam.birdyandroidversion.domain.validation.ValidateLoginInput
import com.birdyteam.birdyandroidversion.domain.validation.ValidationError
import com.birdyteam.birdyandroidversion.domain.validation.ValidationErrorState
import com.birdyteam.birdyandroidversion.domain.validation.ValidationSuccess
import com.birdyteam.birdyandroidversion.presentation.auth.signin.view.LoginView
import io.reactivex.disposables.Disposable
import retrofit2.HttpException
import javax.inject.Inject

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 07.07.2019
 */
@InjectViewState
class LoginPresenter @Inject constructor(
    resources: Resources,
    private val validateLoginInput: ValidateLoginInput,
    private val signInInteractor: SignInInteractor,
    checkAuthorizedInteractor: CheckAuthorizedInteractor
) : MvpPresenter<LoginView>() {

    private val tag = LoginPresenter::class.java.simpleName
    private var authRequest: Disposable? = null
    private var checkAuth: Disposable? = null
    private val errors = resources.getStringArray(R.array.validation_errors)

    init {
        App.appComponent.inject(this@LoginPresenter)
        checkAuth = checkAuthorizedInteractor.checkSignIn()
            .subscribe({
                viewState.signIn()
                Log.d(tag, "${it.id} ${it.token}")
            }, {
                Log.d(tag, "$it")
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        authRequest?.dispose()
        checkAuth?.dispose()
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
        message.append(
            when (passwordErrorMessage?.errorMessage) {
                ValidationErrorState.TOO_SHORT -> " ${errors[1]}"
                ValidationErrorState.TOO_LONG -> " ${errors[3]}"
                ValidationErrorState.NOT_MATCH_PATTERN -> " ${errors[2]}"
                else -> ""
            }
        )
        viewState.showError(message.toString())
    }

    fun signUpClicked() {
        viewState.signUp()
    }
}