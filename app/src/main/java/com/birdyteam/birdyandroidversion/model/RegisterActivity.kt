package com.birdyteam.birdyandroidversion.model

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.birdyteam.birdyandroidversion.R

/*
 * @author: Ilia Ilmenskii
 */
class RegisterActivity : AppCompatActivity() {

    companion object {
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
        }
    }

    override fun onBackPressed() {
        startActivity(LoginActivity.getInstance(this))
    }
}
