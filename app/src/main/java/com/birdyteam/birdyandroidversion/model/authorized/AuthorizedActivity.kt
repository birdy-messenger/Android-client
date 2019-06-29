package com.birdyteam.birdyandroidversion.model.authorized

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.birdyteam.birdyandroidversion.R
import com.birdyteam.birdyandroidversion.model.LoginActivity
import com.birdyteam.birdyandroidversion.model.user.UserFactory

class AuthorizedActivity : AppCompatActivity() {
    companion object {
        const val TAG = "AuthorizedActivity"
        const val AUTHORIZED = "com.birdyteam.birdyandroidversion.model.authorized.authorized_intent"
        fun getInstance(context: Context) : Intent {
            return Intent(context, AuthorizedActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorized)
        supportActionBar?.hide()

        setMenu()
    }

    private fun setMenu() {
        val fragment = supportFragmentManager.findFragmentById(R.id.MenuContainer)
        if(fragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.MenuContainer, MenuFragment())
                .commit()
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
            apply()
        }
    }

    fun menuClicked(id: Int?) {
        when (id) {
            R.id.Settings -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.FragmentContainer, SettingsFragment())
                    .commit()
            }
            R.id.UserProfile -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.FragmentContainer, ProfileFragment())
                    .commit()
            }
        }
    }
}