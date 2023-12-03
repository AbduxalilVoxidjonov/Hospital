package com.example.hospital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Telephony.Mms.Intents
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.hospital.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressLogin.isVisible = false
        binding.view.isVisible = false
        binding.apply {
            createAccount.setOnClickListener {
                val intent = Intent(this@LoginActivity, CreateAccauntActivity::class.java)
                startActivity(intent)
            }
            loginAccaunt.setOnClickListener {
                progressLogin.isVisible = true
                view.isVisible = true
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    val intent =
                        Intent(this@LoginActivity, MainActivity::class.java) // SplashActivity
                    startActivity(intent)
                    finish()
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