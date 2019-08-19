package com.birdyteam.birdyandroidversion.domain.interactor

import com.birdyteam.birdyandroidversion.data.network.api.app.AuthenticationApi
import com.birdyteam.birdyandroidversion.data.network.api.app.response.AuthResponse
import com.birdyteam.birdyandroidversion.domain.input.LoginInput
import com.birdyteam.birdyandroidversion.data.repository.UserAuthInfoRepository
import com.birdyteam.birdyandroidversion.utils.createMD5
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
class SignInInteractor(
    private val userAuthInfoRepository: UserAuthInfoRepository,
    private val authenticationApi: AuthenticationApi
) {
    fun signIn(input: LoginInput): Completable = authenticationApi
        .auth(input.email, input.password.createMD5())
        .subscribeOn(Schedulers.io())
        .map { AuthResponse(it.id, it.token) }
        .flatMapCompletable { userAuthInfoRepository.saveAuthInfo(it) }
}