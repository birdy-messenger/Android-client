package com.birdyteam.birdyandroidversion.jsonparams

import android.util.Log
import com.birdyteam.birdyandroidversion.model.LoginActivity
import com.birdyteam.birdyandroidversion.model.user.UserFactory
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class BirdyJSONUtils {
    companion object {
        private const val ERROR = "ErrorMessage"

        fun createCurrentUser(json : String) {
            try {
                val jsonBody = JSONObject(json)
                val error = jsonBody.isNull(ERROR)
                if(error) {
                    val id = jsonBody.getInt(BirdyJSONKeys.ID)
                    val token = jsonBody.getLong(BirdyJSONKeys.TOKEN)
                    UserFactory.createUser(token, id)
                } else {
                    UserFactory.currentUser = null
                }
            } catch (ioe : IOException) {
                Log.d(LoginActivity.TAG, "Failed to fetch $ioe")
            } catch (je : JSONException) {
                Log.d(LoginActivity.TAG, "Failed to parse JSON $je")
            }
        }

        class BirdyJSONKeys {
            companion object {
                const val ID = "Id"
                const val TOKEN = "Token"
            }
        }
    }
}