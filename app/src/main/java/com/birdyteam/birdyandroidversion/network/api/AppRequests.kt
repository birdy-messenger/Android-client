package com.birdyteam.birdyandroidversion.network.api

import com.birdyteam.birdyandroidversion.network.response.AuthResponse
import com.birdyteam.birdyandroidversion.network.response.RegResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 08.07.2019
 */

interface AppRequests {
    @GET("/api/app/auth")
    fun auth(
        @Query("email") email: String,
        @Query("passwordHash") password: String
    ): Single<AuthResponse>

    @POST("/api/app/reg")
    fun reg(
        @Query("email") email: String,
        @Query("passwordHash") password: String,
        @Query("firstName") name: String
    ): Single<RegResponse>
}