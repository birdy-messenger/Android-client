package com.birdyteam.birdyandroidversion.model

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.nfc.Tag
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.requests.BirdyRequestUtils
import com.birdyteam.birdyandroidversion.view.LoadingFragment
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/*
 * @author: Ilia Ilmenskii
 */
class RegisterActivity : AppCompatActivity(), InterfaceAccessAsync {

    companion object {
        private const val TAG = "RegisterActivity"
        fun getInstance(packageContext : Context) : Intent {
            return Intent(packageContext, RegisterActivity::class.java)
        }
    }

    private lateinit var registerBtn : Button
    private lateinit var enterName : EditText
    private lateinit var enterEmail : EditText
    private lateinit var enterPassword : EditText
    private lateinit var confirmPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        enterName = findViewById(R.id.enter_name)
        enterEmail = findViewById(R.id.enter_email)
        enterPassword = findViewById(R.id.enter_password)
        confirmPassword = findViewById(R.id.confirm_password)

        registerBtn = findViewById(R.id.register_btn)
        registerBtn.setOnClickListener {
            if(checkCorrectness()) {
                val fm = supportFragmentManager
                val dialog = LoadingFragment()
                dialog.show(fm, LoginActivity.LOADING_TAG)
                RegisterUser(
                    dialog,
                    this,
                    enterEmail.text.toString(),
                    enterPassword.text.toString(),
                    enterName.text.toString()
                ).execute()
            }
        }
    }

    private fun checkCorrectness() : Boolean {
        if(checkAllIsEmpty()) {
            makeToast("You've left empty field!")
            return false
        }
        if(!checkEmail()) {
            makeToast("Your email format didn't match pattern!")
            return false
        }
        if(confirmPassword.text.toString() != enterPassword.text.toString()) {
            makeToast("Passwords don't match")
            return false
        }
        Log.d(TAG, "Email matched pattern")
        return true
    }

    private fun checkAllIsEmpty(): Boolean {
        return enterEmail.text.isEmpty() || confirmPassword.text.isEmpty() ||
                enterPassword.text.isEmpty() || enterName.text.isEmpty()
    }

    private fun checkEmail() = enterEmail.text.contains(Regex("[\\w]+@[\\w]+\\.[\\w]+"))

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        startActivity(LoginActivity.getInstance(this))
    }

    override fun registerAccess(access: String) {
        Log.d(TAG, access)
    }

    class RegisterUser(
        private val dialog: LoadingFragment,
        private val accessAsync: InterfaceAccessAsync?,
        private val email : String,
        private val password : String,
        private val firstName : String
    )
        : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg p0: Void?): String {
            val builtUri = Uri.parse(
                BirdyRequestUtils.HTTP +
                        BirdyRequestUtils.BIRDY +
                        BirdyRequestUtils.API +
                        BirdyRequestUtils.BirdyRequestMethods.USER_REG
            )
                .buildUpon()
                .appendQueryParameter(
                    BirdyRequestUtils.BirdyRequestMethods.BirdyRequestAuthParams.PASSWORD, password
                )
                .appendQueryParameter(
                    BirdyRequestUtils.BirdyRequestMethods.BirdyRequestAuthParams.EMAIL, email
                )
                .appendQueryParameter(
                    BirdyRequestUtils.BirdyRequestMethods.BirdyRequestAuthParams.FIRST_NAME, firstName
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
            accessAsync?.registerAccess(result)
        }
    }
}
