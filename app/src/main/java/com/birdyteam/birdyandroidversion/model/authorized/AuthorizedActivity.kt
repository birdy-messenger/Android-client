package com.birdyteam.birdyandroidversion.model.authorized

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.model.LoginActivity
import com.birdyteam.birdyandroidversion.model.user.UserFactory

class AuthorizedActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val AUTHORIZED = "com.birdyteam.birdyandroidversion.model.authorized.authorized_intent"
        fun getInstance(context: Context) : Intent {
            return Intent(context, AuthorizedActivity::class.java)
        }
    }

    private lateinit var allUsersBtn : ImageView
    private lateinit var sendMessageBtn : ImageView
    private lateinit var userProfileBtn : ImageView
    private lateinit var settingsBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorized)
        supportActionBar?.hide()

        allUsersBtn = findViewById(R.id.AllUsers)
        allUsersBtn.setOnClickListener(this)
        sendMessageBtn = findViewById(R.id.SendMessage)
        sendMessageBtn.setOnClickListener(this)
        userProfileBtn = findViewById(R.id.UserProfile)
        userProfileBtn.setOnClickListener(this)
        settingsBtn = findViewById(R.id.Settings)
        settingsBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

        val fm = supportFragmentManager

        when (p0?.id) {
            R.id.AllUsers -> {

            }
            R.id.SendMessage -> {

            }
            R.id.UserProfile -> {

            }
            R.id.Settings -> {
                fm.beginTransaction()
                    .replace(R.id.FragmentContainer, SettingsFragment())
                    .commit()
            }
        }
    }

    fun logout() {
        cleanSharedPreferences()
        UserFactory.currentUser = null
        val mIntent = LoginActivity.getInstance(this)
        startActivity(mIntent)
        finish()
    }

    private fun cleanSharedPreferences() {
        getSharedPreferences(LoginActivity.LOGIN_ACTIVITY,Context.MODE_PRIVATE).edit {
            clear()
            commit()
        }
    }
}