package com.birdyteam.birdyandroidversion.data.network.api.app

import com.birdyteam.birdyandroidversion.data.network.api.app.body.RegistrationBody
import com.birdyteam.birdyandroidversion.data.network.api.app.response.AuthResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 08.07.2019
 */

interface AuthenticationApi {
    @GET("/app/auth")
    fun auth(
        @Query("email") email: String,
        @Query("passwordHash") password: String
    ): Single<AuthResponse>

    @POST("/app/reg")
    fun reg(
        @Body registrationBody: RegistrationBody
    ): Completable
}