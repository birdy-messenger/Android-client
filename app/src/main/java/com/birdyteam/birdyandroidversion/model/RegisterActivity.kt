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
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.requests.AsyncTaskServerRequest
import com.birdyteam.birdyandroidversion.requests.BirdyRequestUtils
import com.birdyteam.birdyandroidversion.requests.RequestID
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
                AsyncTaskServerRequest(
                    this,
                    RequestID.USER_REG,
                    arrayOf(enterEmail.text.toString(),
                        enterPassword.text.toString(),
                        enterName.text.toString())
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
}
