package com.birdyteam.birdyandroidversion.model

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.jsonutils.BirdyJSONUtils
import com.birdyteam.birdyandroidversion.model.authorized.AuthorizedActivity
import com.birdyteam.birdyandroidversion.model.user.UserFactory
import com.birdyteam.birdyandroidversion.requests.AsyncTaskServerRequest
import com.birdyteam.birdyandroidversion.requests.RequestID


/*
 * @author: Ilia Ilmenskii
 */
class LoginActivity : AppCompatActivity(), InterfaceAccessAsync, CancelBroadcastReceiver.OnCancelBroadcast {

    companion object {
        const val LOGIN_ACTIVITY = "com.birdyteam.birdyandroidversion.model.login_activity"
        const val TAG = "LoginActivity"
        const val SAVED_ID = "saved.id"
        const val SAVED_TOKEN = "saved.token"
        const val INVALID_ID = -1
        const val INVALID_TOKEN = -1L
        const val LOADING_TAG = "loading.data.tag"

        fun getInstance(packageContext : Context) : Intent {
            return Intent(packageContext, LoginActivity::class.java)
        }
    }

    override var cancelReceiver: CancelBroadcastReceiver? = null
    private var task : AsyncTaskServerRequest? = null
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

        cancelReceiver = CancelBroadcastReceiver(this)
        registerReceiver(cancelReceiver, IntentFilter(InterfaceAccessAsync.CANCEL))
    }

    override fun onStop() {
        super.onStop()
        task?.cancel(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(cancelReceiver)
    }

    private fun checkSharedPreferences() {
        val pref = getSharedPreferences(LOGIN_ACTIVITY,Context.MODE_PRIVATE)
        val id = pref.getInt(SAVED_ID, INVALID_ID)
        val token = pref.getLong(SAVED_TOKEN, INVALID_TOKEN)
        if(id != INVALID_ID && token != INVALID_TOKEN) {
            Log.d(TAG, "Successfully logged by SharedPreferences")
            UserFactory.createUser(token, id)
            singIn()
        }
    }

    private fun singIn() {
        startActivity(
            AuthorizedActivity.getInstance(this)
        )
    }

    private fun savePreferences(id: Int, token: Long) {
        val pref = getPreferences(Context.MODE_PRIVATE)
        pref.edit {
            putInt(SAVED_ID, id)
            putLong(SAVED_TOKEN, token)
            commit()
        }
    }

    override fun registerAccess(access: String) {
        Log.d(TAG, access)
        BirdyJSONUtils.createCurrentUser(access)
        val currentUser = UserFactory.currentUser
        if(currentUser != null) {
            savePreferences(currentUser.userId, currentUser.accessToken)
            singIn()
        } else {
            makeToast(getString(R.string.failed_to_sing_in))
        }
    }

    override fun onCancel() {
        task?.cancel(true)
    }

    private fun makeToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
