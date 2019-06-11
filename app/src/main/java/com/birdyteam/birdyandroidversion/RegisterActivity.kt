package com.birdyteam.birdyandroidversion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
 * @author: Ilia Ilmenskii
 */
class RegisterActivity : AppCompatActivity() {

    companion object {
        fun getInstance(packageContext : Context) : Intent {
            return Intent(packageContext, RegisterActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
    }
}
