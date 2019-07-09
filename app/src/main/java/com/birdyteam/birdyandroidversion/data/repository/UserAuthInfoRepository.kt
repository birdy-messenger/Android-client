package com.birdyteam.birdyandroidversion.data.repository

import com.birdyteam.birdyandroidversion.data.network.api.app.input.Info
import io.reactivex.Completable

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 09.07.2019
 */
interface UserAuthInfoRepository {
    fun saveInfo(info : Info) : Completable
}