package com.birdyteam.birdyandroidversion.requests

import android.net.Uri
import java.net.URL

class BirdyRequestUtils {
    companion object {
        const val HTTP = "https://"
        const val BIRDY = "birdytestapi.azurewebsites.net/"
        const val API = "api/"

        fun createRequest(id : RequestID, args : Array<String>?) : URL {
            return when (id) {
                RequestID.AUTH -> {
                    val buildUri = Uri.parse(
                        HTTP +
                            BIRDY +
                            API +
                            BirdyRequestMethods.AUTH
                    ).buildUpon()
                        .appendQueryParameter(BirdyRequestMethods.BirdyRequestParams.EMAIL, args!![0])
                        .appendQueryParameter(BirdyRequestMethods.BirdyRequestParams.PASSWORD, args[1])
                        .build()
                    URL(buildUri.toString())
                }
                RequestID.USER_REG -> {
                    val buildUri = Uri.parse(
                        HTTP +
                                BIRDY +
                                API +
                                BirdyRequestMethods.USER_REG
                    ).buildUpon()
                        .appendQueryParameter(BirdyRequestMethods.BirdyRequestParams.EMAIL, args!![0])
                        .appendQueryParameter(BirdyRequestMethods.BirdyRequestParams.PASSWORD, args[1])
                        .appendQueryParameter(BirdyRequestMethods.BirdyRequestParams.FIRST_NAME, args[2])
                        .build()
                    URL(buildUri.toString())
                }
            }
        }
    }
    class BirdyRequestMethods {
        companion object {
            const val AUTH = "auth?"
            const val USER_REG = "user.reg?"
        }
        class BirdyRequestParams {
            companion object {
                const val EMAIL = "email"
                const val PASSWORD = "passwordHash"
                const val FIRST_NAME = "firstName"
            }
        }
    }
}