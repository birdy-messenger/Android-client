package com.birdyteam.birdyandroidversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
 * @author: Ilia Ilmenskii
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        supportActionBar?.hide()
    }
}
