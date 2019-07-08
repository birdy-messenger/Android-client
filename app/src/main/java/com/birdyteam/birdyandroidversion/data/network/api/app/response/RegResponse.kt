package com.birdyteam.birdyandroidversion.data.network.api.app.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @project Android-client
 * @author Ilia Ilmenskii created on 08.07.2019
 */
data class RegResponse(
    @SerializedName("result")
    @Expose
    val result: String
)