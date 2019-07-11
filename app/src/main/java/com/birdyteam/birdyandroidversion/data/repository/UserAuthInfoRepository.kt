package com.birdyteam.birdyandroidversion.data.repository

import com.birdyteam.birdyandroidversion.data.network.api.app.response.AuthResponse
import io.reactivex.Completable

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
interface UserAuthInfoRepository {
    fun saveInfo(info: AuthResponse): Completable
}