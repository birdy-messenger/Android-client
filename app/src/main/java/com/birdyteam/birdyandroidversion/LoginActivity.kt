package com.birdyteam.birdyandroidversion

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import java.util.*
import java.util.concurrent.TimeUnit

/*
 * @author: Ilia Ilmenskii
 */
class LoginActivity : AppCompatActivity() {

    companion object {
        private const val LOADING_TAG = "loading.data.tag"
        fun getInstance(packageContext : Context) : Intent {
            return Intent(packageContext, LoginActivity::class.java)
        }
    }

    private lateinit var registerBtn : Button
    private lateinit var loginBtn : Button

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
            ConnectToServer(dialog).execute()
        }
    }

    class ConnectToServer(private val dialog: LoadingFragment) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg p0: Void?): Void? {
            //Make HTTP Request
            TimeUnit.SECONDS.sleep(2)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            if(dialog.dialog.isShowing) {
                dialog.dismissAllowingStateLoss()
            }
        }
    }
}
