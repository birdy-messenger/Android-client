package com.birdyteam.birdyandroidversion.data.repository

import com.birdyteam.birdyandroidversion.data.network.api.app.response.AuthResponse
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
interface UserAuthInfoRepository {
    fun saveAuthInfo(info: AuthResponse): Completable
    fun getAuthInfo() : Single<AuthResponse>
}