package com.birdyteam.birdyandroidversion.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import com.birdyteam.birdyandroidversion.view.LoadingFragment
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.model.user.UserFactory
import com.birdyteam.birdyandroidversion.requests.AsyncTaskServerRequest
import com.birdyteam.birdyandroidversion.requests.BirdyRequestUtils
import com.birdyteam.birdyandroidversion.requests.RequestID
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL



/*
 * @author: Ilia Ilmenskii
 */
class LoginActivity : AppCompatActivity(), InterfaceAccessAsync {

    companion object {
        private const val TAG = "LoginActivity"
        private const val SAVED_ID = "saved.id"
        private const val SAVED_TOKEN = "saved.token"
        const val LOADING_TAG = "loading.data.tag"

        fun getInstance(packageContext : Context) : Intent {
            return Intent(packageContext, LoginActivity::class.java)
        }
    }

    private lateinit var registerBtn : Button
    private lateinit var loginBtn : Button
    private lateinit var loginEditText : EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        supportActionBar?.hide()

        registerBtn = findViewById(R.id.register_btn)
        registerBtn.setOnClickListener {
            startActivity(
                RegisterActivity.getInstance(this)
            )
        }

        loginBtn = findViewById(R.id.login_btn)
        loginBtn.setOnClickListener {
            AsyncTaskServerRequest(
                true,
                this,
                RequestID.AUTH,
                arrayOf(loginEditText.text.toString(),
                    passwordEditText.text.toString())
            ).execute()
        }

        loginEditText = findViewById(R.id.entered_login)
        passwordEditText = findViewById(R.id.entered_password)

        checkSharedPreferences()
    }

    private fun checkSharedPreferences() {
        val pref = getPreferences(Context.MODE_PRIVATE)
        val id = pref.getInt(SAVED_ID, -1)
        val token = pref.getLong(SAVED_TOKEN, -1)
        if(id != -1 && token != -1L) {
            UserFactory.createUser(token, id)
            makeToast("Logged :-)")
        }
    }

    private fun savePreferences(id: String, token: Long) {
        val pref = getPreferences(Context.MODE_PRIVATE)
        pref.edit {
            putInt(SAVED_ID, id.toInt())
            putLong(SAVED_TOKEN, token)
            commit()
        }
    }

    override fun registerAccess(access: String) {
        Log.d(TAG, access)
        try {
            val jsonBody = JSONObject(access)
            val errorMsg = jsonBody.isNull("errorMessage")
            if(errorMsg) {
                val id = jsonBody.getString("id")
                val token = jsonBody.getLong("token")
                Log.d(TAG,"Auth was successful with id=$id and token=$token")
                savePreferences(id, token)
                UserFactory.createUser(token, id.toInt())
            } else {
                Log.d(TAG, "Error occurred")
                makeToast(jsonBody.getString("errorMessage"))
            }
        } catch (ioe : IOException) {
            Log.d(TAG, "Failed to fetch $ioe")
        } catch (je : JSONException) {
            Log.d(TAG, "Failed to parse JSON $je")
        }
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
