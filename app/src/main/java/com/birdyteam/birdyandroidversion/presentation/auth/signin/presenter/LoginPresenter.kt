package com.birdyteam.birdyandroidversion.presentation.auth.signin.presenter

import android.content.res.Resources
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.birdyteam.birdyandroidversion.App
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.domain.input.LoginInput
import com.birdyteam.birdyandroidversion.domain.interactor.ReadAuthInfoInteractor
import com.birdyteam.birdyandroidversion.domain.interactor.SignInInteractor
import com.birdyteam.birdyandroidversion.domain.validation.*
import com.birdyteam.birdyandroidversion.presentation.auth.signin.view.LoginView
import io.reactivex.disposables.CompositeDisposable
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
    readAuthInfoInteractor: ReadAuthInfoInteractor
) : MvpPresenter<LoginView>() {

    private val tag = LoginPresenter::class.java.simpleName
    private val disposablesContainer = CompositeDisposable()
    private val errors = resources.getStringArray(R.array.validation_errors)

    init {
        App.appComponent.inject(this@LoginPresenter)
        disposablesContainer.add(
            readAuthInfoInteractor.checkSignIn()
                .subscribe({
                    viewState.signIn()
                    Log.d(tag, "${it.id} ${it.token}")
                }, {
                    Log.d(tag, "$it")
                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposablesContainer.dispose()
    }

    fun signInClicked(email: String, password: String) {
        when (val result = validateLoginInput.validate(LoginInput(email, password))) {
            is LoginValidationError -> showValidationError(result)
            is ValidationSuccess -> {
                viewState.showLoad()
                disposablesContainer.add(
                    signInInteractor.signIn(LoginInput(email, password))
                        .subscribe({
                            viewState.hideLoad()
                            viewState.signIn()
                        }, {
                            viewState.hideLoad()
                            val message = (it as HttpException).response()
                            Log.e(tag, message.toString())
                        })
                )
            }
        }
    }

    private fun showValidationError(result: LoginValidationError) = result.apply {
        val message = StringBuilder()
        if (emailError != null)
            message.append(errors[0])
        if (passwordError != null)
            message.append(
                when (passwordError as Errors) {
                    Errors.TOO_SHORT -> " ${errors[1]}"
                    Errors.TOO_LONG -> " ${errors[3]}"
                    Errors.NOT_MATCH_PATTERN -> " ${errors[2]}"
                }
            )
        viewState.showError(message.toString())
    }

    fun signUpClicked() {
        viewState.signUp()
    }
}