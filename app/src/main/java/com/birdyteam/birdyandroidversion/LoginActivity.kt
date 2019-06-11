package com.birdyteam.birdyandroidversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

/*
 * @author: Ilia Ilmenskii
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var registerBtn : Button

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
    }
}
