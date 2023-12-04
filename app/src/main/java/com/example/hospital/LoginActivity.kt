package com.example.hospital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.hospital.database.AppDatabase
import com.example.hospital.databinding.ActivityLoginBinding
import com.example.hospital.database.Entity.LoginEntity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var appDatabase: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDatabase = AppDatabase.getInstanse(this@LoginActivity)

        binding.progressLogin.isVisible = false
        binding.view.isVisible = false
        binding.apply {
            createAccount.setOnClickListener {
                val intent = Intent(this@LoginActivity, CreateAccauntActivity::class.java)
                startActivity(intent)
            }

            loginAccaunt.setOnClickListener {
                val email = textUserName.text.toString()
                val password = textPassword.text.toString()
                progressLogin.isVisible = true
                view.isVisible = true
                Handler().postDelayed({
                    if (appDatabase.registerAccaunt().getLogin(email, password)) {
                        appDatabase.loginAccaunt()
                            .getlogin(LoginEntity(email = email, passwor = password))

                        val intent =
                            Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {

                        Toast.makeText(
                            this@LoginActivity,
                            "Email or password is incorrect",
                            Toast.LENGTH_SHORT
                        ).show()
                        progressLogin.isVisible = false
                        view.isVisible = false
                    }
                }, 2000)


            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.progressLogin.isVisible = false
        binding.view.isVisible = false
    }
}