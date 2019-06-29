package com.birdyteam.birdyandroidversion.requests

import android.net.Uri
import android.util.Log
import com.birdyteam.birdyandroidversion.hash.MD5Utils
import com.birdyteam.birdyandroidversion.model.LoginActivity
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
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
                RequestID.GET_USER_INFO -> {
                    val buildUri = Uri.parse(
                        HTTP +
                                BIRDY +
                                API +
                                BirdyRequestMethods.USER_GET
                    ).buildUpon()
                        .appendQueryParameter(BirdyRequestMethods.BirdyRequestParams.ID, args!![0])
                        .appendQueryParameter(BirdyRequestMethods.BirdyRequestParams.TOKEN, args[1])
                        .build()
                    URL(buildUri.toString())
                }
            }
        }

        fun response(url: URL): ByteArrayOutputStream? {
            val connection = url.openConnection() as HttpURLConnection
            try {
                val input : InputStream = if(connection.responseCode != HttpURLConnection.HTTP_OK)
                    connection.errorStream
                else
                    connection.inputStream
                val out = ByteArrayOutputStream()
                var bytesRead : Int
                val buffer = ByteArray(1024)
                while(input.read(buffer).also { bytesRead = it } > 0) {
                    out.write(buffer, 0, bytesRead)
                }
                return out
            } catch (e : Exception) {
                Log.d(LoginActivity.TAG, "$e")
                return null
            }
            finally {
                connection.disconnect()
            }
        }
    }
    class BirdyRequestMethods {
        companion object {
            const val AUTH = "auth?"
            const val USER_REG = "user/reg?"
            const val USER_GET = "user.get?"
        }
        class BirdyRequestParams {
            companion object {
                const val EMAIL = "email"
                const val PASSWORD = "passwordHash"
                const val FIRST_NAME = "firstName"
                const val ID = "id"
                const val TOKEN = "token"
            }
        }
    }
}