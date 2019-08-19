package com.birdyteam.birdyandroidversion.domain.interactor

import com.birdyteam.birdyandroidversion.data.network.api.app.response.AuthResponse
import com.birdyteam.birdyandroidversion.data.repository.UserAuthInfoRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 12.07.2019
 */
class ReadAuthInfoInteractor(
    private val userAuthInfoRepository: UserAuthInfoRepository
) {
    fun checkSignIn(): Single<AuthResponse> = userAuthInfoRepository
        .getAuthInfo()
        .subscribeOn(Schedulers.io())
}