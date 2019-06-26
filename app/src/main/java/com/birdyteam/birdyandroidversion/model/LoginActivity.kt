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
import com.birdyteam.birdyandroidversion.view.LoadingFragment
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.requests.BirdyRequestUtils
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
        private const val LOADING_TAG = "loading.data.tag"
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
            val fm = supportFragmentManager
            val dialog = LoadingFragment()
            dialog.show(fm, LOADING_TAG)
            ConnectToServer(
                dialog,
                this,
                loginEditText.text.toString(),
                passwordEditText.text.toString()
            ).execute()
        }

        loginEditText = findViewById(R.id.entered_login)
        passwordEditText = findViewById(R.id.entered_password)
    }

    override fun accessLogin(access: String) {
        Log.d(TAG, access)
        try {
            val jsonBody = JSONObject(access)
            val errorMsg = jsonBody.isNull("errorMessage")
            if(errorMsg) {
                val id = jsonBody.getString("id")
                val token = jsonBody.getLong("token")
                Log.d(TAG,"Auth was successful with id=$id and token=$token")
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

    class ConnectToServer(private val dialog: LoadingFragment,
                          private var access: InterfaceAccessAsync?,
                          private val email : String,
                          private val password : String)
        : AsyncTask<Void, Void, String>() {


        override fun doInBackground(vararg p0: Void?): String {
            val builtUri = Uri.parse(
                BirdyRequestUtils.HTTP +
                        BirdyRequestUtils.BIRDY +
                        BirdyRequestUtils.API +
                        BirdyRequestUtils.BirdyRequestMethods.LOGIN
            )
                .buildUpon()
                .appendQueryParameter(
                    BirdyRequestUtils.BirdyRequestMethods.BirdyRequestAuthParams.EMAIL, email
                )
                .appendQueryParameter(
                    BirdyRequestUtils.BirdyRequestMethods.BirdyRequestAuthParams.PASSWORD, password
                )
                .build()
            val url = URL(builtUri.toString())
            val connection = url.openConnection() as HttpURLConnection
            try {
                val input : InputStream = if(connection.responseCode != HttpURLConnection.HTTP_OK)
                    connection.errorStream
                else
                    connection.inputStream
                val out = ByteArrayOutputStream()
                var bytesRead : Int
                val buffer = ByteArray(1024)
                while(input.read(buffer).also {
                        bytesRead = it
                    }
                    > 0)
                {
                    out.write(buffer, 0, bytesRead)
                }
                    out.close()
                return out.toString()
            } catch (e : Exception) {
                Log.d(TAG, "$e")
                return ""
            }
            finally {
                connection.disconnect()
            }
        }

        override fun onPostExecute(result: String) {
            super.onPostExecute(result)
            if(dialog.dialog.isShowing) {
                dialog.dismissAllowingStateLoss()
            }
            access?.accessLogin(result)
        }
    }
}
