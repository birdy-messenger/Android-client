package com.birdyteam.birdyandroidversion.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 08.07.2019
 */

data class AuthResponse(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("token")
    @Expose
    val token: Long
)