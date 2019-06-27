package com.birdyteam.birdyandroidversion.requests

import android.net.Uri
import com.birdyteam.birdyandroidversion.hash.MD5Utils
import java.net.URL

class BirdyRequestUtils {
    companion object {
        private const val HTTP = "https://"
        private const val BIRDY = "birdytestapi.azurewebsites.net/"
        private const val API = "api/"

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
                        .appendQueryParameter(
                            BirdyRequestMethods.BirdyRequestParams.PASSWORD,
                            MD5Utils.createMd5(args[1])
                        )
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
                        .appendQueryParameter(BirdyRequestMethods.BirdyRequestParams.PASSWORD, MD5Utils.createMd5(args[1]))
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